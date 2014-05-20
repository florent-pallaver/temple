<?php

namespace temple\data\persistence\model;

/**
 * Description of ModelProxy
 *
 * @author florent
 */
trait ModelProxy {

	private $property ;

	private $holder ;
	
	private $instance ;
	
	public function __construct(\ReflectionProperty $property, Model $holder) {
		parent::__construct();
		$this->property = $property ;
		$this->holder = $holder ;
	}
	
	public function __destruct() {
		\temple\Logger::getInstance()->debug('Destroying proxy [' . parent::_class() . ', ' . $this->getId() . ']');
	}
	
	private function lazyLoad() {
		if(!$this->instance) {
			$this->instance = ModelManager::getInstance()->findById(parent::_class(), $this->getId()) ;
			$p = $this->property->isPrivate() ;
			if($p) {
				$this->property->setAccessible(true) ;
			}
			$this->property->setValue($this->holder, $this->instance) ;
			if($p) {
				$this->property->setAccessible(false) ;
			}
		}
	}
	
}
