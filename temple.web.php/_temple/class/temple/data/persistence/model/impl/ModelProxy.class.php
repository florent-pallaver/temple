<?php

namespace temple\data\persistence\model\impl;

/**
 * Description of ModelProxy
 *
 * @author florent
 */
abstract class ModelProxy implements \temple\data\persistence\model\Model {

	/**
	 * @var \temple\data\persistence\model\ModelManager
	 */
	private static $mm ;
	
	private $id ;
	
	private $class ;
	
	public function getClass() {
		return $this->class ;
	}

	public function getId() {
		return $this->id ;
	}

	public function jsonSerialize() {
		
	}

	private function load() {
		self::$mm->findById($this->class, $this->id) ;
	}
	
	private static function _init() {
		self::$mm = \temple\data\persistence\model\ModelManager::getInstance() ;
	}
	
}
