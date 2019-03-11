<?php

namespace temple\data\persistence\model\impl;

use ReflectionProperty ;
use temple\data\persistence\model\Model ;

/**
 * Description of ModelUtil
 *
 * @author florent
 */
abstract class ModelUtil {
	
	private function __construct() {}
	
	/**
	 * 
	 * @param \temple\data\persistence\model\Model $m
	 * @param ReflectionProperty $property
	 * @return mixed
	 */
	public static final function getPropertyValue(Model $m, ReflectionProperty $property) {
		$p = $property->isPrivate() || $property->isProtected() ;
		if($p) {
			$property->setAccessible(true) ;
		}
		$v = $property->getValue($m) ;
		if($p) {
			$property->setAccessible(false) ;
		}
		return $v ;
	}

	/**
	 * 
	 * @param \temple\data\persistence\model\Model $m
	 * @param ReflectionProperty $property
	 * @param mixed $value
	 */
	public static final function setPropertyValue(Model $m, ReflectionProperty $property, $value) {
		$p = $property->isPrivate() || $property->isProtected() ;
		if($p) {
			$property->setAccessible(true) ;
		}
		$v = $property->setValue($m, $value) ;
		if($p) {
			$property->setAccessible(false) ;
		}
	}

}
