<?php

namespace temple\data\persistence\model;

/**
 * Description of AbstractMapping
 *
 * @author florent
 */
abstract class AbstractMapping extends \temple\WithLogger implements Mapping {

	/**
	 * @var \ReflectionProperty
	 */
	protected $field ;
	
	private $pop ;
	
	private $insertable ;
	
	private $updatable ;
	
	public function __construct(\ReflectionProperty $field, $insertable, $updatable) {
		parent::__construct() ;
		$this->field = $field ;
		$this->pop = $field->isPrivate() || $field->isProtected() ;
		$this->insertable = $insertable ;
		$this->updatable = $updatable ;	
	}
	
	public final function isInsertable() {
		return $this->insertable;
	}

	public final function isUpdatable() {
		return $this->updatable;
	}
	
	protected final function getValue(Model $m) {
		if($this->pop) {
			$this->field->setAccessible(true) ;
		}
		$v = $this->field->getValue($m) ;
		if($this->pop) {
			$this->field->setAccessible(false) ;
		}
		return $v ;
	}
	
	protected final function setValue(Model $m, $v) {
		if($this->pop) {
			$this->field->setAccessible(true) ;
		}
		$this->field->setValue($m, $v) ;
		if($this->pop) {
			$this->field->setAccessible(false) ;
		}
	}
	
}
