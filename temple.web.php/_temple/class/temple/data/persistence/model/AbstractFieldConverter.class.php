<?php

namespace temple\data\persistence\model;

/**
 * Description of AbstractFieldConverter
 *
 * @author florent
 */
abstract class AbstractFieldConverter 
	extends \temple\WithLogger 
	implements FieldConverter {
	
	protected function __construct() {
		parent::__construct() ;
	}
	
	public final function toDBValue($value) {
//		$this->logger->finest('converting ' . ($value === null ? 'NULL' : $value) . ' to DB value') ;
		return $value === null ? self::NULL_DB_VALUE : $this->toDBValue0($value) ;
	}

	protected abstract function toDBValue0($notNullValue) ;
	
	public final function toPHPValue($value) {
//		$this->logger->finest('converting ' . ($value === null ? 'NULL' : $value)  . ' to PHP value') ;
		return $value === null ? null : $this->toPHPValue0($value) ;
	}
	
	protected abstract function toPHPValue0($notNullValue) ;

}
