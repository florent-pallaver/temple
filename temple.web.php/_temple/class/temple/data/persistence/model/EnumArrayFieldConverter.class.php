<?php

namespace temple\data\persistence\model;

/**
 * Description of EnumArrayFieldConverter
 *
 * @author florent
 */
class EnumArrayFieldConverter extends AbstractFieldConverter {
	
	const GET_ALL_METHOD = 'getAll' ;
	
	private static $converters = [] ;
	
	/**
	 * @var \ReflectionClass
	 */
	private $enumClass ;
	
	protected function __construct(\ReflectionClass $enumClass) {
		parent::__construct() ;
		$this->enumClass = $enumClass;
	}
	
	protected function toDBValue0($notNullArray) {
		$mask = 0 ;
		foreach($notNullArray as $v) {
			$mask |= 1 << $v->getOrdinal() ;
		}
		return $mask ;
	}

	protected function toPHPValue0($notNullMask) {
		$array = [] ;
		$all = $this->enumClass->getMethod(self::GET_ALL_METHOD)->invoke(null) ;
		foreach($all as $v) {
			if($notNullMask & (1 << $v->getOrdinal())) {
				$array[] = $v ;
			}
		}
		return $array;
	}

	/**
	 * 
	 * @param \ReflectionClass $enumClass
	 * @return FieldConverter
	 */
	public static function getInstance(\ReflectionClass $enumClass) {
		$en = $enumClass->getName() ;
		if(!isset(self::$converters[$en])) {
			self::$converters[$en] = new EnumArrayFieldConverter($enumClass) ;
		}
		return self::$converters[$en] ;
	}
	
}
