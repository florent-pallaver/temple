<?php

namespace temple\data\persistence ;

use \ReflectionClass ;
use \ReflectionProperty ;
use \ReflectionPropertyException ;

use query\Field ;
use query\Comparison ;
use query\AbstractQuery ;
use query\SelectQuery ;
use query\QueryException ;

use temple\data\model\Model;
use temple\Logger;
use temple\IllegalArgumentException ;

/**
 * TODOC
 *
 * @author florent
 */
final class OldDescriptor {

	/**
	 * @var string TODOC
	 */
	public static $DEFAULT_MODEL_NAMESPACE = '\\data\\persistence\\model' ;

	private static $dbConnection = null ;

	private static $instances = array() ;

	private $className ;

	private $class ;

	private $table ;

	private $primaryKey ;

	private $fields ;

	private $excluded ;

	private $insertables ;

	private $updatables ;

	private $formatters ;

	private $children ;

	private $autoFetch ;

	private $relations ;

	private function __construct($className) {
		$this->className = $className ;
		$this->class = new ReflectionClass($className) ;
		$this->autoSetStaticProperty('table', Descriptor::getTableName($className)) ;
		$this->autoSetStaticProperty('primaryKey', 'id') ;
		$this->autoSetStaticProperty('excluded', array()) ;
		$this->autoSetStaticProperty('insertables', array()) ;
		$this->autoSetStaticProperty('updatables', array()) ;
		$this->autoSetStaticProperty('formatters') ;
		$this->autoSetStaticProperty('children') ; // TODEL ?
		$this->autoSetStaticProperty('autoFetch') ; // TODEL
		$this->autoSetStaticProperty('relations', array()) ;
		foreach($this->relations as $r) {
			$this->excluded[] = $r->getField() ;
			if(PersistenceHelper::isXToOne($r)) {
				$this->excluded[] = $r->getMappingField() ;
			}
		}
		$this->fields = array() ;
		foreach($this->class->getProperties(ReflectionProperty::IS_PUBLIC) as $p) {
			if(!($p->isStatic() || in_array($p->getName(), $this->excluded))) {
				$this->fields[] = $p->getName() ;
			}
		}
	}

	private function autoSetStaticProperty($property, $default = null) {
		try {
			$this->$property = $this->class->getStaticPropertyValue($property) ;
		} catch(ReflectionException $re) {
			$this->$property = $default ;
		}
	}

	private static function getTableName($className) {
		$lbs = strrpos($className, '\\') ;
		return strtolower(substr($className, $lbs + 1, -5)) ; // 5 = 'Model' length
	}

	public static function getInstance($className) {
		if($className && $className[0] != '\\') {
			$className = self::$DEFAULT_MODEL_NAMESPACE . $className ;
		}
		if(!isset(Descriptor::$instances[$className])){
			Descriptor::$instances[$className] = new Descriptor($className) ;
		}
		return Descriptor::$instances[$className] ;
	}

	public static function setDbConnection(MySQLi $dbConnection) {
		self::$dbConnection = $dbConnection ;
	}

	// Getters
	public function getClassName() {
		return $this->className ;
	}

	public function getTable() {
		return $this->table ;
	}

	public function getPrimaryKeyField() {
		return $this->primaryKey ;
	}

	public function getFields() {
		return $this->fields ;
	}

	public function getInsertableFields() {
		return $this->insertables ;
	}

	public function getUpdatableFields() {
		return $this->updatables ;
	}

	public function getRelations() {
		return $this->relations ;
	}

	// Queries
	public function getAllByCondition(array $condition, $length = 0) {
		return $this->getAll($this->getCondition($condition), $length) ;
	}

	public function getAll($condition = '', $length = 0, $start = 0, $calcFoundRows = false) {
		$limitedQuery = 'SELECT '. ($calcFoundRows ? 'SQL_CALC_FOUND_ROWS' : '') ." * FROM {$this->table} " . $condition ;
		if($length > 0) {
			$limitedQuery .= ' LIMIT ' ;
			if($start > 0) {
				$limitedQuery .= $start . ', ' ;
			}
			$limitedQuery .= $length ;
		}
		$models = array() ;
		$result = $this->execute($limitedQuery) ;
		if($result) {
			for( ; $m = $result->fetch_object($this->className) ; $models[] = $m) ;
			$result->free() ;
			if(count($this->children) > 0) {
				foreach($models as $parent) {
					foreach($this->children as $field => $className) {
						$parent->$field = Descriptor::getInstance($className)->getAllByParent($parent) ;
					}
				}
			}
			if(count($this->autoFetch) > 0) {
				foreach($models as $model) {
					foreach($this->autoFetch as $field => $infos) {
						$fieldId = isset($infos['foreignKey']) ? $infos['foreignKey'] : $field . 'Id' ;
						$model->$field = Descriptor::getInstance($infos['class'])->getByPrimaryKey($model->$fieldId) ;
					}
				}
			}
		}
		return $length == 1 ? count($models) == 1 ? $models[0] : null : $models ;
	}

	public function insert($toInsert, array $onDuplicateKey = array(), $ignore = false) {
		$models = $this->getAsModelArray($toInsert);
		$modelsValues = array() ;
		foreach($models as $model) {
			$values = array() ;
			foreach($this->insertables as $f) {
				$values[] = $this->format($f, $model->$f) ;
			}
			$modelsValues[] = '(' . implode($values, ', ') . ')' ;
		}
		$ign = $ignore ? 'IGNORE' : '' ;
		$query = "INSERT $ign INTO {$this->table} (" . implode($this->insertables, ', ') . ') VALUES ' . implode($modelsValues, ', ') ;
		if($onDuplicateKey) {
			$updates = array() ;
			foreach($onDuplicateKey as $f => $v) {
				if($this->isFieldUpdatable($f)) {
					$updates[] = $f . ' = ' . $this->format($f, $v) ;
				}
			}
			$query .= ' ON DUPLICATE KEY UPDATE ' . implode(', ', $updates) ;
		}
		if($this->execute(new _DescriptorQuery($this->className, $query))) {
			$r = self::$dbConnection->affected_rows ;
			if($onDuplicateKey) {
				$modelsCount = count($models) ;
				$r = array(($modelsCount << 1) - $r, $r - $modelsCount) ;
			}
		} else {
			$r = false ;
		}
		return $r ;
	}

	public function update(Model $model, array $fieldsToUpdate = array(), array $condition = null) {
		if(count($fieldsToUpdate) == 0) {
			$fieldsToUpdate = $this->updatables ;
		}
		$sets = array() ;
		foreach($fieldsToUpdate as $f) {
			if($this->isFieldUpdatable($f)) {
				$sets[] = $f .' = '. $this->format($f, $model->$f) ;
			}
		}
		$query = new _DescriptorQuery($this->className, 'UPDATE ' . $this->table . ' SET ' . implode(', ', $sets) .
			$this->getCondition($condition === null ? $model->getPrimaryKey() : $condition)) ;
		return $this->execute($query) ? self::$dbConnection->affected_rows : 0;
	}

	private function isFieldUpdatable($field) {
		return in_array($field, $this->updatables) ;
	}

	public function delete(Model $model) {
		$query = 'DELETE FROM ' . $this->table . $this->getCondition($model->getPrimaryKey()) ;
		return $this->execute($query) ? self::$dbConnection->affected_rows : 0;
	}

	public function getCondition($fields) {
		$where = ' WHERE ' ;
		if(is_array($fields)) {
			$and = array() ;
			foreach($fields as $k => $f) {
				if(is_array($f)) {
					$o = $f['cmp'] ;
					$v = $f['val'] ;
				} else {
					$o = '=' ;
					$v = $f ;
				}
				$and[] = $k . ' ' . $o . ' ' . $this->format($k, $v) ;
			}
			$cond = implode(' AND ', $and) ;
		} else {
			$cond = $this->primaryKey . ' = ' . $this->format($this->primaryKey, $fields) ;
		}
		return strlen($cond) == 0 ? '' : $where . $cond ;
	}

	private function format($f, $v) {
		$v = $v === null ? null : self::$dbConnection->real_escape_string($v) ;
		return isset($this->formatters[$f]) ? call_user_func($this->formatters[$f], $v) : $v ;
	}

	private function getAsModelArray($arg) {
		if(is_array($arg)) {
			$models = $arg ;
		} else if($arg instanceof Model) {
			$models = array($arg) ;
		} else {
			throw new IllegalArgumentException($arg . ' is neither an array nor a Model object') ;
		}
		return $models ;
	}

	private function execute($query) {
		if(self::$dbConnection == null) {
			throw new QueryException($query, 'No connection to the database has been provided!') ;
		}
		$result = self::$dbConnection->query($query) ;
		if(!$result) {
			throw new QueryException($query, self::$dbConnection->error) ;
		}
		return $result ;
	}

	public static function _init() {
		// autoset connection if it exists
		if(DB::$connection != null) {
			self::setDbConnection(DB::$connection) ;
		}
	}
}

