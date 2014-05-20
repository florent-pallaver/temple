<?php

namespace temple\data\persistence\model;

/**
 * Description of AbstractFieldConverter
 *
 * @author florent
 */
abstract class AbstractFieldConverter implements FieldConverter {
	
	protected function __construct() {}
	
	public final function toDBValue($value) {
		return $value === null ? self::NULL_DB_VALUE : $this->toDBValue0($value) ;
	}

	protected abstract function toDBValue0($notNullValue) ;
	
	public final function toPHPValue($value) {
		return $value === null ? null : $this->toPHPValue0($value) ;
	}
	
	protected abstract function toPHPValue0($notNullValue) ;

}
