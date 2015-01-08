<?php

namespace temple\data\persistence\model\impl ;

use temple\data\persistence\db\query\Table;
use temple\data\persistence\model\RelationMapping;
use temple\data\persistence\model\ManyToOne;
use temple\data\persistence\model\OneToOne;
use temple\data\persistence\db\query\QueryFactory;

/**
 * TODOC
 *
 * @author florent
 */
class GraphArrow {

	private static $entryArrowName = '_r' ;

	private static $namePrefix = '_t' ;

	private static $i = 0 ;

	// actually the table alias name
	private $name ;

	private $table ;

	private $mapping ;

	private $node ;

	private $cyclic ;

	private $traversable ;

	/**
	 * Constructor.
	 * TODOC
	 *
	 * @param Mapping $arrow
	 * @param GraphNode $node
	 * @param unknown $cyclic - whether this arc is pointing to its origin node or not.
	 */
	public function __construct(GraphNode $node, RelationMapping $mapping = null, $cyclic = false) {
		$this->name = $mapping ? self::$namePrefix . self::$i++ : self::$entryArrowName ;
		$this->table = QueryFactory::getInstance()->newTable($node->getName(), $this->name) ;
		$this->mapping = $mapping ;
		$this->node = $node ;
		$this->cyclic = $cyclic ;
		$this->traversable = ($mapping instanceof ManyToOne || $mapping instanceof OneToOne) ;
	}

	/**
	 * TODOC
	 * @return string -  the unique name of this arc.
	 */
	public function getName() {
		return $this->name ;
	}

	/**
	 * @return Table TODOC
	 *
	 */
	public function getTable() {
		return $this->table ;
	}

	/**
	 * TODOC
	 *
	 * @return \temple\data\persistence\model\RelationMapping
	 */
	public function getMapping() {
		return $this->mapping ;
	}

	/**
	 * TODOC
	 *
	 * @return GraphNode
	 */
	public function getNode() {
		return $this->node ;
	}

	/**
	 * TODOC
	 *
	 * @return boolean
	 */
	public function isCyclic() {
		return $this->cyclic ;
	}

	/**
	 *
	 * TODOC
	 * @return boolean
	 */
	public function isTraversable() {
		return $this->traversable ;
	}

}
