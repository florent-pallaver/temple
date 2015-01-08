<?php

namespace temple\data\persistence\model;

use temple\Logger ;

/**
 * Description of ModelProxy
 *
 * @author florent
 */
trait ModelProxy {

	/**
	 *
	 * @var \ReflectionProperty
	 */
	private $property ;

	private $holder ;
	
	private $instance ;
	
	public function proxyInit(\ReflectionProperty $property = null, Model $holder = null) {
		$this->property = $property ;
		$this->holder = $holder ;
	}
	
	public function __destruct() {
//		error_log('Destroying proxy ['.parent::_class()->getName().', '.$this->getId().']') ;
//		Logger::getInstance('PROXY')->debug('Destroying proxy ['.parent::_class()->getName().', '.$this->getId().']');
	}
	
	private function lazyLoad() {
		if(!$this->instance) {
			Logger::getInstance('PROXY')->debug('Lazily loading ['.parent::_class()->getName().', '.$this->getId().']');
			// setter les fields parents a la place c'est mieux !
			$this->instance = ModelManager::getInstance()->findByKey(parent::getPK(), $this->getId()) ;
			if($this->property && $this->holder) {
//				Logger::getInstance('PROXY')->severe(gettype($this->property)) ;
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
	
}
