<?php

namespace temple\data\persistence\model\impl;

use temple\data\persistence\model\RelationMapping;
use temple\data\persistence\model\ManyToOne;
use temple\data\persistence\model\OneToOne;
use temple\data\persistence\model\Mapping;
use temple\data\persistence\db\query\Table;
use temple\data\persistence\db\query\QueryFactory;
use temple\data\persistence\db\query\Field;
use temple\data\persistence\db\query\JoinType;

/**
 * TODOC
 *
 * @author florent
 */
class GraphArrow {

    /**
     * @var QueryFactory
     */
    private static $qf;
    private static $entryArrowName = '_r';
    private static $namePrefix = '_t';
    private static $i = 0;
    // actually the table alias name
    private $name;
    private $table;
    private $resolvedTable;
    private $resolvedFields;
    private $mapping;
    private $node;
    private $cyclic;
    private $traversable;

    /**
     * Constructor.
     * TODOC
     *
     * @param Mapping $arrow
     * @param GraphNode $node
     * @param unknown $cyclic - whether this arc is pointing to its origin node or not.
     */
    public function __construct(GraphNode $node, RelationMapping $mapping = null, $cyclic = false) {
        $this->name = $mapping ? self::$namePrefix . self::$i++ : self::$entryArrowName;
        $this->table = QueryFactory::getInstance()->newTable($node->getName(), $this->name);
        $this->resolvedTable = null;
        $this->resolvedFields = [];
        $this->mapping = $mapping;
        $this->node = $node;
        $this->cyclic = $cyclic;
        $this->traversable = ($mapping instanceof ManyToOne || $mapping instanceof OneToOne);
    }

    /**
     * TODOC
     * @return string -  the unique name of this arc.
     */
    public function getName() {
        return $this->name;
    }

    /**
     * @return Table TODOC
     */
    public function getTable() {
        return QueryFactory::getInstance()->newTable($this->node->getName(), $this->name);
    }

    /**
     * TODOC
     *
     * @return \temple\data\persistence\model\RelationMapping
     */
    public function getMapping() {
        return $this->mapping;
    }

    /**
     * TODOC
     *
     * @return GraphNode
     */
    public function getNode() {
        return $this->node;
    }

    /**
     * TODOC
     *
     * @return boolean
     */
    public function isCyclic() {
        return $this->cyclic;
    }

    /**
     *
     * TODOC
     * @return boolean
     */
    public function isTraversable() {
        return $this->traversable;
    }

    /**
     * @return Table
     */
    public function getResolvedTable() {
        $this->ensureResolution();
        return $this->resolvedTable;
    }

    /**
     * @return array 
     */
    public function getResolvedFields() {
        $this->ensureResolution();
        return $this->resolvedFields;
    }

    private function ensureResolution() {
        if ($this->resolvedTable == null) {
            $this->resolvedTable = self::resolveTableAndFields($this, $this->resolvedFields);
        }
    }

    /**
     * 
     * @param \temple\data\persistence\model\impl\GraphArrow $graphArrow
     * @param array $fields
     * @return Table
     */
    private static function resolveTableAndFields(GraphArrow $graphArrow, array &$cols, array &$visitedNodes = []) {
        $n = $graphArrow->getNode();
        $t = $graphArrow->getTable();
        $visitedNodes[] = $n ;
        foreach ($n->getMetamodel()->getMappings() as $mapping) {
            self::addColumns($mapping, $t, $cols);
        }
        foreach ($n->getMetamodel()->getRelationMappings() as $mapping) {
            self::addColumns($mapping, $t, $cols) ;
        }
        foreach ($n->getExitArrows($visitedNodes) as $a) {
            if ($a instanceof GraphArrow && $a->isTraversable()) {
                $m = $a->getMapping();
                if ($m->autoFetch()) {
                    $jt = self::resolveTableAndFields($a, $cols, $visitedNodes);
                    $jc = self::$qf->newAndCondition();
                    $tfn = $m->getColumnNames();
                    $jtfn = $m->getMappedKey()->getColumnNames();
                    for ($i = 0, $l = count($tfn); $i < $l; $i++) {
                        $jc->addComparison(self::$qf->newFieldComparison(new Field($tfn[$i], $t), new Field($jtfn[$i], $jt)));
                    }
                    $t->join($m->isOptionnal() ? JoinType::$LEFT_OUTER_JOIN : JoinType::$INNER_JOIN, $jt, $jc);
//                } else {
//                    self::addColumns($m, $t, $cols);
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

    private static function _init() {
        self::$qf = QueryFactory::getInstance();
    }

}
