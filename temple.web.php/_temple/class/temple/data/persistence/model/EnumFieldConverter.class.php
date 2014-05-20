<?php

namespace temple\data\persistence\model;

/**
 * Description of EnumFieldConverter
 *
 * @author florent
 */
class EnumFieldConverter extends AbstractFieldConverter {
	
	const GET_BY_ORDINAL_METHOD = 'getByOrdinal' ;
	
	private static $converters = [] ;
	
	/**
	 *
	 * @var \ReflectionClass
	 */
	private $enumClass ;
	
	protected function __construct(\ReflectionClass $enumClass) {
		parent::__construct() ;
		$this->enumClass = $enumClass;
	}
	
	protected function toDBValue0($notNullValue) {
		return $notNullValue->getOrdinal() ;
	}

	protected function toPHPValue0($notNullValue) {
		return $this->enumClass->getMethod(self::GET_BY_ORDINAL_METHOD)->invoke(null, $notNullValue) ;
	}

	/**
	 * 
	 * @param \ReflectionClass $enumClass
	 * @return FieldConverter
	 */
	public static function getInstance(\ReflectionClass $enumClass) {
		$en = $enumClass->getName() ;
		if(!isset(self::$converters[$en])) {
			self::$converters[$en] = new EnumFieldConverter($enumClass) ;
		}
		return self::$converters[$en] ;
	}
	
}
