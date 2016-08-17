<?php

namespace temple\data\persistence\db\query\impl ;

use temple\util\Util;
use temple\data\persistence\db\query\Table;
use temple\data\persistence\db\query\Condition;
use temple\data\persistence\db\query\JoinType;

/**
 * TODOC
 *
 * @author florent
 */
class TableImpl extends \temple\data\persistence\db\query\AbstractQueryPart implements Table {

	/**
	 * TODOC
	 *
	 * @var string
	 */
	const ALIAS_PREFIX = 't' ;

	private static $aliasIndex = 0 ;

	private $name ;

	private $alias ;

	private $joins ;
	
	private $joinedTables ;

	/**
	 * Constructor.
	 *
	 * @param string $name - this table name
	 * @param string $alias - this table alias
	 * @throws IllegalArgumentException if the given name is not a string or an empty or NULL string.
	 */
	public function __construct($name, $alias = null) {
		Util::notEmptyString($name) ;
		$this->name = $name ;
		$this->alias = $alias ? $alias : (self::ALIAS_PREFIX . self::$aliasIndex++) ;
		$this->joins = [] ;
		$this->joinedTables = [] ;
	}

	public function getName() {
		return $this->name ;
	}

	public function getAlias() {
		return $this->alias ;
	}

	public function getJoinedTables() {
		return $this->joinedTables ;
	}

	public function join(JoinType $joinType, Table $table, Condition $condition = null) {
		$this->joins[] = new JoinImpl($joinType, $table, $condition) ;
		$this->joinedTables[] = $table ;
	}

	public function toString() {
		return $this->name . ' AS ' . $this->alias . implode(' ', $this->joins) ;
	}

}