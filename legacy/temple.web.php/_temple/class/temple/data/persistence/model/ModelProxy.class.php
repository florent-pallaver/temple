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
	 * @var Logger
	 */
	private static $_templeLogger ;
	
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
	
	public function _instance() {
		$this->lazyLoad() ;
		return $this->instance ;
	}
	
	private function lazyLoad() {
		if(!$this->instance) {
			if(self::$_templeLogger->isDebugLoggable()) {
				self::$_templeLogger->debug('Lazily loading ['.parent::_class()->getName().', '.$this->getId().']');
			}
			// setter les fields parents a la place c'est mieux !
			$this->instance = ModelManager::getInstance()->findByKey(parent::getPK(), $this->getId()) ;
			if($this->property && $this->holder) {
				if(self::$_templeLogger->isFinestLoggable()) {
					self::$_templeLogger->finest('setting property ' . $this->property);
				}
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
	
	private static function _initModelProxy() {
		self::$_templeLogger = Logger::getInstance('temple.proxy[' . __CLASS__ . ']') ;
	}
	
}
