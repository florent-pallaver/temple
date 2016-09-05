<?php

namespace temple\data\persistence\model\impl;

use temple\data\persistence\db\query\Field;
use temple\data\persistence\db\query\QueryFactory;
use temple\data\persistence\db\query\Comparison;
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
     * @var QueryFactory
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
     * @return GraphArrow
     */
    public function getEntryArrow() {
        return $this->entryArrow;
    }

    /**
     * @return string
     */
    public function getName() {
        return $this->name;
    }

    /**
     * @return \temple\data\persistence\model\Metamodel
     */
    public function getMetamodel() {
        return $this->metamodel;
    }

    /**
     * 
     * @param array $notGoingTo list of GraphNode to avoid
     * @return array
     */
    public function getExitArrows(array $notGoingTo = []) {
        $exitArrows = $this->arrows ;
        if($notGoingTo) {
            $exitArrows = [] ;
            foreach($this->arrows as $arrow) {
                if(array_search($arrow->getNode(), $notGoingTo, true) === FALSE) {
                    $exitArrows[] = $arrow ;
                }
            }
        }
        return $exitArrows;
    }

    /**
     * 
     * @param \temple\data\persistence\db\query\Comparison $comp
     * @param type $maxCount
     * @param type $offset
     * @return \temple\data\persistence\db\query\Select
     */
    public function newSelect(Comparison $comp, $maxCount = 0, $offset = 0) {
        $table = $this->entryArrow->getResolvedTable();
        $fields = $this->entryArrow->getResolvedFields();
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
        $f = [];
        $v = [];
        foreach ($this->metamodel->getMappings() as $m) {
            $this->setFieldsAndValues($model, $m, $f, $v);
        }
        foreach ($this->metamodel->getRelationMappings() as $rm) {
            if ($rm instanceof \temple\data\persistence\model\ManyToOne || $rm instanceof \temple\data\persistence\model\OneToOne) {
                $this->setFieldsAndValues($model, $rm, $f, $v);
            }
        }
        return self::$qf->newInsert($this->metamodel->getTableName())->setFields($f)->addTuple($v);
    }

    private function setFieldsAndValues(Model $model, Mapping $m, &$fields, &$values) {
        if ($m->isInsertable()) {
            foreach ($m->getColumnNames() as $cn) {
                $fields[] = $cn;
            }
            foreach ($m->getDBValue($model) as $dv) {
                $values[] = $dv;
            }
        }
    }

    public function newUpdate(Model $model) {
        $t = $this->entryArrow->getTable();
        $u = self::$qf->newUpdate($t);
        foreach ($this->metamodel->getMappings() as $m) {
            if ($m->isUpdatable()) {
                $cols = $m->getColumnNames();
                $vals = $m->getDBValue($model);
                for ($i = 0, $l = count($cols); $i < $l; $i++) {
                    $u->addAssignment(new Field($cols[$i], $t), $vals[$i]);
                }
            }
        }
        foreach ($this->metamodel->getRelationMappings() as $rm) {
            if ($rm instanceof \temple\data\persistence\model\ManyToOne || $rm instanceof \temple\data\persistence\model\OneToOne) {
                if ($rm->isUpdatable()) {
                    $cols = $rm->getColumnNames();
                    $vals = $rm->getDBValue($model);
                    for ($i = 0, $l = count($cols); $i < $l; $i++) {
                        $u->addAssignment(new Field($cols[$i], $t), $vals[$i]);
                    }
                }
            }
        }
        $u->getCondition()->addComparison(self::$qf->newKeyComparison($this->metamodel->getPrimaryKey(), $model->getId(), $t));
        return $u;
    }

    /**
     * 
     * @param \temple\data\persistence\db\query\Comparison $comp
     * @param type $maxCount
     * @param type $offset
     * @return \temple\data\persistence\db\query\Delete
     */
    public function newDelete(Comparison $comp, $maxCount = 0, $offset = 0) {
        $table = $this->entryArrow->getResolvedTable();
        $d = self::$qf->newDelete($table, $maxCount, $offset);
        $d->getCondition()->addComparison($comp);
        return $d;
    }

    public function extractObject0(array $tuple) {
        $node = $this->entryArrow->getNode();
        $instance = $node->metamodel->newInstance();
        foreach ($node->metamodel->getMappings() as $m) {
            $m->setPHPValue($instance, self::getValue($m, $tuple));
        }
        foreach ($node->metamodel->getRelationMappings() as $m) {
            $m->setPHPValue($instance, self::getValue($m, $tuple));
        }
        return $instance;
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

    private static function object(GraphArrow $arrow, array $tuple, array &$alreadyVisitedNodes = []) {
        $node = $arrow->getNode();
        $alreadyVisitedNodes[] = $node ;
        $instance = $node->metamodel->newInstance();
        $colPrefix = $arrow->getName() . '_';
        $allFieldsNull = true;
        foreach ($node->metamodel->getMappings() as $m) {
            $v = self::getValue($m, $tuple, $colPrefix);
            $allFieldsNull = ($m->setPHPValue($instance, $v) === null) && $allFieldsNull;
        }
        if ($allFieldsNull && $arrow->getMapping()->isOptionnal()) {
            $instance = null;
        } else {
            foreach ($node->getExitArrows() as $a) {
                $m = $a->getMapping();
                if ((array_search($a->getNode(), $alreadyVisitedNodes, true) === FALSE) && $a->isTraversable()) {
                    $v = $m->autoFetch() ? self::object($a, $tuple, $alreadyVisitedNodes) : self::getValue($m, $tuple, $colPrefix);
                    $m->setPHPValue($instance, $v);
                } else {
                    $m->setPHPValue($instance, self::getValue($m, $tuple, $colPrefix));
                }
            }
        }
        return $instance;
    }

    private static function getValue(Mapping $m, array $tuple, $colPrefix = '') {
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
