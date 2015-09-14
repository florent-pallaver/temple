<?php

namespace temple\data\persistence\db\query\impl ;

use temple\data\persistence\db\query\Table;
use temple\data\persistence\db\query\JoinType;
use temple\data\persistence\db\query\Condition;

/**
 * Describes an SQL Join.
 *
 * @author florent
 */
class Join extends \temple\data\persistence\db\query\AbstractQueryPart {

	/**
	 * @var JoinType
	 */
	private $type ;

	/**
	 * @var Table
	 */
	private $table ;

	/**
	 * @var Condtion
	 */
	private $condition ;

	/**
	 * Constructor.
	 * TODOC
	 *
	 * @param JoinType $type
	 * @param Table $table
	 * @param Comparison $comparison
	 */
	public function __construct(JoinType $type, Table $table, Condition $condition = null) {
		$this->type = $type ;
		$this->table = $table ;
		$this->condition = $condition ;
	}

	public function toString() {
		return vsprintf($this->type->getFormat(), array($this->table, $this->condition)) ;
	}

}
