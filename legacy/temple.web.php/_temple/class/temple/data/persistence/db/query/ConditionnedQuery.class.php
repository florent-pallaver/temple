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
	 * @return array an array of OrderBy
	 */
	function getOrders() ;

	/**
	 * 
	 * @param OrderBy $orderBy
	 * @return ConditionnedQuery
	 */
	function addOrderBy(OrderBy $orderBy) ;
	
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