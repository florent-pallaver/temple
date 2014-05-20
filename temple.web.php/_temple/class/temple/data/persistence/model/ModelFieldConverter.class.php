<?php

namespace temple\data\persistence\model;

use ReflectionClass ;

/**
 * Description of ModelFieldConverter
 *
 * @author florent
 */
class ModelFieldConverter extends AbstractFieldConverter {

	private static $instances = [] ;

	/**
	 * @var \ReflectionClass
	 */
	private $modelClass ;
	
	protected function __construct(ReflectionClass $modelClass) {
		parent::__construct();
		$this->modelClass = $modelClass ;
	}
	
	protected function toDBValue0($notNullValue) {
		return $notNullValue->getId() ;
	}

	protected function toPHPValue0($notNullValue) {
		if(is_object($notNullValue) && $this->modelClass->isInstance($notNullValue)) {
			return $notNullValue ;
		} else {
			\temple\Logger::getInstance()->debug($notNullValue) ;
			// generate proxy
		}
	}

	/**
	 * @param \ReflectionClass $rc
	 * @return ModelFieldConverter
	 */
	public static function getInstance(ReflectionClass $rc) {
		$n = $rc->getName() ;
		if(!isset(self::$instances[$n])) {
			self::$instances[$n] = new ModelFieldConverter($rc) ;
		}
		return self::$instances[$n] ;
	}
	
	
}
