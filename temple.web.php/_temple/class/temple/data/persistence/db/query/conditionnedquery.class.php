<?php

namespace temple\data\persistence\db\query ;

/**
 * TODOC
 *
 * @author florent
 */
interface ConditionnedQuery extends Query {

	/**
	 * TODOC
	 * @return Condition
	 */
	function getCondition() ;

	/**
	 * TODOC
	 * @return array
	 */
	function getOrders() ;

	/**
	 * TODOC
	 *
	 * @param unknown $field
	 * @param string $asc
	 */
	function addOrderedField(Field $field, $asc = false) ;

	/**
	 * TODOC
	 *
	 * @param array $fields
	 * @param string $asc
	 */
	function addOrderedFields(array $fields, $asc = false) ;

	// 	public final function addRandomOrder() {
	// 		$this->orders[] = 'RAND()' ;
	// 		return $this ;
	// 	}

	/**
	 * TODOC
	 */
	public function getOffset() ;

	/**
	 * TODOC
	 */
	public function getMaxCount() ;

}