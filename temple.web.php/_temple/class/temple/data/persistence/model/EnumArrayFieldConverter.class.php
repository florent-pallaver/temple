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
	
	protected function __construct($enumClass) {
		parent::__construct() ;
		$this->enumClass = new \ReflectionClass($enumClass);
	}
	
	protected function toDBValue0($notNullArray) {
		$mask = 0 ;
		foreach($notNullArray as $v) {
			$mask |= 1 << $v->ordinal() ;
		}
		return $mask ;
	}

	protected function toPHPValue0($notNullMask) {
		$array = [] ;
		$all = $this->enumClass->getMethod(self::GET_ALL_METHOD)->invoke(null) ;
		foreach($all as $v) {
			if($notNullMask & (1 << $v->ordinal())) {
				$array[] = $v ;
			}
		}
		return $array;
	}

	/**
	 * 
	 * @param string $enumClass
	 * @return FieldConverter
	 */
	public static function getInstance($enumClass) {
		if(!isset(self::$converters[$enumClass])) {
			self::$converters[$enumClass] = new EnumArrayFieldConverter($enumClass) ;
		}
		return self::$converters[$enumClass] ;
	}
	
}
