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
	
	/**
	 *
	 * @var \ReflectionProperty
	 */
	private $property ;
	
	/**
	 * @var \ReflectionClass
	 */
	private $proxyClass ;
	
	protected function __construct(ReflectionClass $modelClass) {
		parent::__construct();
		$this->modelClass = $modelClass ;
		$this->proxyClass = new \ReflectionClass(ProxyGenerator::PROXY_NAMESPACE . $modelClass->getName() . 'Proxy') ;
	}
	
	protected function toDBValue0($notNullValue) {
		return $notNullValue->getId() ;
	}

	protected function toPHPValue0($notNullValue) {
		if(is_object($notNullValue) && $this->modelClass->isInstance($notNullValue)) {
			$o = $notNullValue ;
		} else {
			$o = $this->proxyClass->newInstance() ;
			$o->proxyInit($this->property, $notNullValue) ;
		}
		return $o ;
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
