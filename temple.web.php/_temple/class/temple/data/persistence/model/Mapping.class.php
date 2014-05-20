<?php

namespace temple\data\persistence\model;

/**
 * Description of Mapping
 *
 * @author florent
 */
interface Mapping {

	function getColumnNames() ;
	
	function isInsertable() ;

	function isUpdatable() ;
		
	/**
	 * @param \temple\data\persistence\model\Model $m
	 * @return array
	 */
	function getDBValue(Model $m) ;

	function setPHPValue(Model $m, $value) ;
	
}
