<?php

namespace temple\data\persistence\model\impl;

use temple\data\persistence\db\query\Field;
use temple\data\persistence\db\query\JoinType;
use temple\data\persistence\db\query\QueryFactory;
use temple\data\persistence\db\query\Comparison;
use temple\data\persistence\db\query\Table;
use temple\data\persistence\model\Mapping;
use temple\data\persistence\model\RelationMapping;
use temple\data\persistence\model\Model;
use temple\data\persistence\model\ModelManager;

/**
 * TODOC
 *
 * @author florent
 */
class GraphNode {

	private static $graphs = [];

	/**
	 * @var QueryFactory TODOC
	 */
	private static $qf;

	/**
	 * @var GraphArrow
	 */
	private $entryArrow;

	/** @var \temple\data\persistence\model\Metamodel */
	private $metamodel;
	// actually the table name
	private $name;
	private $arrows;

	private function __construct(\ReflectionClass $modelClass) {
		// register first for cyclic graph!
		self::$graphs[$modelClass->getName()] = $this;

		$this->metamodel = ModelManager::getInstance()->getMetamodel($modelClass);
		$this->name = $modelClass->getStaticPropertyValue('TABLE');
		$this->entryArrow = new GraphArrow($this);
		$this->arrows = [];

		foreach ($this->metamodel->getRelationMappings() as $rm) {
			if ($rm instanceof RelationMapping) {
				$g = self::getInstance($rm->getMappedKey()->getClass());
				$this->arrows[] = new GraphArrow($g, $rm, $this === $g);
			}
		}
	}

	/**
	 * TODOC
	 *
	 * @return GraphArrow
	 */
	public function getEntryArrow() {
		return $this->entryArrow;
	}

	/**
	 * TODOC
	 * @return string
	 */
	public function getName() {
		return $this->name;
	}

	/**
	 * TODOC
	 * @return array
	 */
//	public function getArrows() {
//		return $this->arrows;
//	}

	/**
	 * TODOC
	 *
	 * @param string $fieldName
	 * @return GraphArrow
	 */
//	public function getArrow($fieldName) {
//		return _iod($this->arrows, $fieldName);
//	}

	/**
	 * 
	 * @param \temple\data\persistence\db\query\Comparison $comp
	 * @param type $maxCount
	 * @param type $offset
	 * @return type
	 */
	public function newSelect(Comparison $comp, $maxCount = 0, $offset = 0) {
		$fields = [];
		$table = self::table($this->entryArrow, $fields);
		$s = self::$qf->newSelect($table, $fields, $maxCount, $offset);
		$s->getCondition()->addComparison($comp);
		return $s;
	}

	/**
	 * 
	 * @param \temple\data\persistence\model\Model $model
	 * @return \temple\data\persistence\db\query\Insert
	 */
	public function newInsert(Model $model) {
		$i = self::$qf->newInsert($this->metamodel->getTableName());
		$f = [];
		$v = [];
		foreach ($this->metamodel->getMappings() as $m) {
			if ($m->isInsertable()) {
				foreach($m->getColumnNames() as $cn) {
					$f[] = $cn ;
				}
				foreach($m->getDBValue($model) as $dv) {
					$v[] = $dv ;
				}
			}
		}
		return $i->setFields($f)->addTuple($v);
	}

	public function newDelete(Comparison $comp, $maxCount = 0, $offset = 0) {
		
	}

	/**
	 * 
	 * @param \temple\data\persistence\model\impl\GraphArrow $graphArrow
	 * @param array $fields
	 * @return type
	 */
	private static function table(GraphArrow $graphArrow, array &$cols = null) {
		$n = $graphArrow->getNode();
		$t = $graphArrow->getTable();
		if ($cols !== null) {
			foreach ($n->metamodel->getMappings() as $mapping) {
				self::addColumns($mapping, $t, $cols);
			}
		}
		if (!$graphArrow->isCyclic()) {
			foreach ($n->arrows as $a) {
				if ($a instanceof GraphArrow && $a->isTraversable()) {
					$m = $a->getMapping();
					if ($cols !== null && !$m->autoFetch()) {
						self::addColumns($m, $t, $cols);
					}
					$jt = self::table($a, $m->autoFetch() ? $cols : null);
					$jc = self::$qf->newAndCondition();
					$tfn = $m->getColumnNames();
					$jtfn = $m->getMappedKey()->getColumnNames();
					for ($i = 0, $l = count($tfn); $i < $l; $i++) {
						$jc->addComparison(self::$qf->newFieldComparison(new Field($tfn[$i], $t), new Field($jtfn[$i], $jt)));
					}
					$t->join($m->isOptionnal() ? JoinType::$LEFT_OUTER_JOIN : JoinType::$INNER_JOIN, $jt, $jc);
				}
			}
		}
		return $t;
	}

	private static function addColumns(Mapping $m, Table $t, array &$cols) {
		foreach ($m->getColumnNames() as $cn) {
			$cols[] = new Field($cn, $t, $t->getAlias() . '_' . $cn);
		}
	}

	/**
	 * TODOC
	 *
	 * @param array $tuple
	 * @return Model
	 */
	public function extractObject(array $tuple) {
		return self::object($this->entryArrow, $tuple);
	}

	private static function object(GraphArrow $arrow, array $tuple) {
		$node = $arrow->getNode();
		$instance = $node->metamodel->newInstance() ;
		$colPrefix = $arrow->getName() . '_';
		$allFieldsNull = true;
		foreach ($node->metamodel->getMappings() as $m) {
			$v = self::getValue($m, $tuple, $colPrefix);
			$allFieldsNull = ($m->setPHPValue($instance, $v) === null) && $allFieldsNull;
		}
		if ($allFieldsNull && $arrow->getMapping()->isOptionnal()) {
			$instance = null;
		} elseif (!$arrow->isCyclic()) {
			foreach ($node->arrows as $a) {
				if ($a->isTraversable()) {
					$m = $a->getMapping();
					$v = $m->autoFetch() ? self::object($a, $tuple) : self::getValue($m, $tuple, $colPrefix) ;
					$m->setPHPValue($instance, $v) ;
				}
			}
		}
		return $instance;
	}

	private static function getValue(Mapping $m, array $tuple, $colPrefix) {
		$value = [];
		foreach ($m->getColumnNames() as $cn) {
			$value[] = _iod($tuple, $colPrefix . $cn);
		}
		return count($value) == 1 ? $value[0] : $value;
	}

	/**
	 * TODOC
	 *
	 * @param string $modelClassName
	 * @return GraphNode
	 */
	public static function getInstance(\ReflectionClass $modelClass) {
		$cn = $modelClass->getName();
		return isset(self::$graphs[$cn]) ? self::$graphs[$cn] : new GraphNode($modelClass);
	}

	private static function _init() {
		self::$qf = QueryFactory::getInstance();
	}

}
