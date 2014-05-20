<?php

namespace temple\data\persistence\model;

/**
 * Description of FieldConverter
 *
 * @author florent
 */
interface FieldConverter {

	const NULL_DB_VALUE = 'NULL' ;
	
	function toPHPValue($value) ;
	
	function toDBValue($value) ;
	
}
