<?php

namespace temple\data\persistence\db\query ;

use temple\util\Util;

/**
 *
 * TODOC
 *
 * @author florent
 */
interface Table extends QueryPart {

	/**
	 * @return string the name of this table.
	 */
	function getName() ;

	/**
	 * @return string the alias of this table, <code>null</code> if none.
	 */
	function getAlias() ;

	/**
	 * Joins a table to this one.
	 *
	 * @param JoinType $joinType - the join type
	 * @param Table $table - the table to be joined
	 * @param Condition $condition - the condition to join the tables, maybe <code>null</code>
	 */
	function join(JoinType $joinType, Table $table, Condition $condition = null) ;

}