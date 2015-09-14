<?php

namespace temple\data\persistence\model\impl ;

use ReflectionClass ;
use ReflectionProperty ;
use ReflectionException ;

use temple\data\persistence\model\Key ;
use temple\util\Util;

/**
 * TODOC
 *
 * @author florent
 */
final class ModelDescriptor {

	/** @var string TODOC */
	const DEFAULT_PRIMARY_KEY_FIELD_VALUE = 'id' ;

	/**
	 * @var string TODOC
	 */
	const DEFAULT_MODEL_NAMESPACE = __NAMESPACE__ ;

	private static $instances = array() ;

	private $className ;

	/** @var ReflectionClass */
	private $class ;

	private $table ;

	private $primaryKey ;

	private $fields ;

	private $transients ;

	private $insertables ;

	private $updatables ;

	private $formatters ;

	private $relations ;

	private function __construct($className) {
		$this->className = $className ;
		$this->class = new ReflectionClass($className) ;
		$this->autoSetStaticProperty0('primaryKey', self::DEFAULT_PRIMARY_KEY_FIELD_VALUE) ;
		$this->autoSetStaticProperty0('table', self::getTableName($className)) ;
		$this->primaryKey = new Key($className, [$this->primaryKey], true, Key::PRIMARY_KEY_NAME) ;
		
		$parent = $this->class->getParentClass() ;
		$parentDesc = $parent ? self::getInstance($parent->getName()) : null ;
		
		$this->autoSetStaticProperty('transients', $parentDesc ? $parentDesc->transients : []) ;
		$this->autoSetStaticProperty('insertables', $parentDesc ? $parentDesc->insertables : []) ;
		$this->autoSetStaticProperty('updatables', $parentDesc ? $parentDesc->updatables : []) ;
		$this->autoSetStaticProperty('formatters', $parentDesc ? $parentDesc->formatters : []) ;
		$this->autoSetStaticProperty('relations', $parentDesc ? $parentDesc->relations : []) ;
		foreach($this->relations as $r) {
			// we should iterates over the keys ...
			$this->transients[] = $r->getField() ;
			if(self::isXToOne($r)) {
				$this->transients[] = $r->getMappingKey() ;
			}
		}
		$this->fields = $parentDesc ? $parentDesc->fields : [] ;
		foreach($this->class->getProperties() as $p) {
			if(!($p->isStatic() || in_array($p->getName(), $this->transients))) {
				$this->fields[] = $p->getName() ;
			}
		}
	}

	private function autoSetStaticProperty0($property, $default = null) {
		try {
			$this->$property = $this->class->getStaticPropertyValue($property) ;
		} catch(ReflectionException $re) {
			$this->$property = $default ;
		}
	}
	
	private function autoSetStaticProperty($property, array $default) {
		try {
			$p = $this->class->getProperty($property) ;
			if($p->isStatic()) {
				$pr = $p->isPrivate() || $p->isProtected() ;
				if($pr) {
					$p->setAccessible(true) ;
				}
				$this->$property = $default + $p->getValue(null)  ;
				if($pr) {
					$p->setAccessible(false) ;
				}
			}
		} catch(ReflectionException $re) {
			$this->$property = $default ;
		}
	}

	// Getters
	/**
	 * @return string - the model class name this descriptor manages.
	 */
	public function getClassName() {
		return $this->className ;
	}

	public function getTableName() {
		return $this->table ;
	}

	/**
	 * TODOC
	 * @return \temple\data\persistence\model\Key
	 */
	public function getPrimaryKey() {
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

	public function getFormatters() {
		return $this->formatters ;
	}

	public function getMappings() {
		return $this->relations ;
	}

	/**
	 * TODOC
	 *
	 * @return Model
	 */
	public function newInstance() {
		return $this->class->newInstance() ;
	}

	/**
	 * TODOC
	 *
	 * @param string $className
	 * @return ModelDescriptor
	 */
	public static function getInstance($className) {
		Util::notEmptyString($className) ;
		if(!isset(self::$instances[$className])){
			self::$instances[$className] = new ModelDescriptor($className) ;
		}
		return self::$instances[$className] ;
	}

	/**
	 * Returns the default table name to use for a model class.
	 *
	 * @param string $className - a fully qualified class name
	 * @return string the default table name to use for a model class.
	 */
	private static function tableName($className) {
		return strtolower(Util::getSimpleClassName($className)) ;
	}
	
	/**
	 * Tells whether a Relation is a one or many to one relation.
	 *
	 * @param Relation $rel - a relation
	 * @return boolean <code>true</code> if the given Relation is a OneToOne or a ManyToOne, <code>false</code> otherwise.
	 */
	private static function isXToOne(Relation $rel) {
		return $rel instanceof OneToOne || $rel instanceof ManyToOne ;
	}

}
