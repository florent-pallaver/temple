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
	
	protected function __construct($enumClass) {
		parent::__construct() ;
		$this->enumClass = new \ReflectionClass($enumClass);
	}
	
	protected function toDBValue0($notNullValue) {
		return $notNullValue->getOrdinal() ;
	}

	protected function toPHPValue0($notNullValue) {
//		\temple\Logger::getInstance()->debug('getByOrdinal ' . $this->enumClass->name) ;
		return $this->enumClass->getMethod(self::GET_BY_ORDINAL_METHOD)->invoke(null, $notNullValue) ;
	}

	/**
	 * 
	 * @param string $enumClass
	 * @return FieldConverter
	 */
	public static function getInstance($enumClass) {
		if(!isset(self::$converters[$enumClass])) {
			self::$converters[$enumClass] = new EnumFieldConverter($enumClass) ;
		}
		return self::$converters[$enumClass] ;
	}
	
}
