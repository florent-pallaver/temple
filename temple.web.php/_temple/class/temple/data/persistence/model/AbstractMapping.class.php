<?php

namespace temple\data\persistence\model;

/**
 * Description of AbstractMapping
 *
 * @author florent
 */
abstract class AbstractMapping implements Mapping {

	/**
	 * @var \ReflectionProperty
	 */
	protected $field ;
	
	private $pop ;
	
	private $insertable ;
	
	private $updatable ;
	
	/**
	 * @var array 
	 */
	private $constraints ;
	
	/**
	 * @var FieldConverter
	 */
	private $converter ;
	
	public function __construct(\ReflectionProperty $field, $insertable, $updatable, FieldConverter $converter, array $constraints = []) {
		$this->field = $field ;
		$this->pop = $field->isPrivate() || $field->isProtected() ;
		$this->insertable = $insertable ;
		$this->updatable = $updatable ;	
		$this->converter = $converter ;
		$this->constraints = $constraints;
	}
	
	public final function isInsertable() {
		return $this->insertable;
	}

	public final function isUpdatable() {
		return $this->updatable;
	}
	
	public final function getDBValue(Model $m) {
		if($this->pop) {
			$this->field->setAccessible(true) ;
		}
		$v = $this->field->getValue($m) ;
		if($this->pop) {
			$this->field->setAccessible(false) ;
		}
		foreach($this->constraints as $c) {
			$c->validate($v) ;
		}
		return _eia($this->converter->toDBValue($v)) ;
	}

	public final function setPHPValue(Model $m, $value) {
		$v = $this->converter->toPHPValue($value) ;
		if($this->pop) {
			$this->field->setAccessible(true) ;
		}
		$this->field->setValue($m, $v) ;
		if($this->pop) {
			$this->field->setAccessible(false) ;
		}
		return $v ;
	}

}
