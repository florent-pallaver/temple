<?php

namespace temple\data\persistence\db\query ;

use temple\ToImplement ;
use temple\data\persistence\model\Key ;

/**
 * TODOC
 *
 * @author florent
 */
abstract class QueryFactory {

	use ToImplement ;

	/**
	 * TODOC
	 * @return Comparison
	 */
	public abstract function newKeyComparison(Key $key, $value, Table $table = null, $comp = null) ;

	/**
	 * TODOC
	 * @return Comparison
	 */
	public abstract function newFieldComparison(Field $field, $value, $comparator = null) ;

	/**
	 * TODOC
	 * @return Condition
	 */
	public abstract function newAndCondition() ;

	/**
	 * TODOC
	 * @return Condition
	 */
	public abstract function newOrCondition() ;

	/**
	 * TODOC
	 *
	 * @param string $name
	 * @param string $alias
	 * @return Table
	 */
	public abstract function newTable($name, $alias = null) ;

	/**
	 * TODOC
	 *
	 * @param Table $table
	 * @param array $fields
	 * @param number $maxCount
	 * @param number $offset
	 * @return Select
	 */
	public abstract function newSelect(Table $table, array $fields, $maxCount = 0, $offset = 0) ;

	/**
	 * TODOC
	 *
	 * @param Table $table
	 * @param number $maxCount
	 * @param number $offset
	 * @return Update
	 */
	public abstract function newUpdate(Table $table, $maxCount = 0, $offset = 0) ;

	/**
	 * TODOC
	 *
	 * @param Table $table
	 * @param number $maxCount
	 * @param number $offset
	 * @return Delete
	 */
	public abstract function newDelete(Table $table, $maxCount = 0, $offset = 0) ;

	/**
	 * TODOC
	 *
	 * @param string $tableName
	 * @return Insert
	 */
	public abstract function newInsert($tableName) ;

}
