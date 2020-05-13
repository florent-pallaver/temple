<?php

namespace temple\util;

/**
 * Description of AbstractCollection
 *
 * @author florent
 */
abstract class AbstractCollection implements Collection {

	/**
	 * @var \ReflectionClass
	 */
	private $class ;

	/**
	 * @var array
	 */
	private $array ;

	protected function __construct(\ReflectionClass $class) {
		$this->class = $class;
		$this->array = [];
	}
	
	public final function getElementClass() {
		return $this->class ;
	}

	public function add(\temple\TempleObject $to) {
		if(!$this->class->isInstance($to)) {
			throw new \RuntimeException('This collection only accepts instances of ' . $this->class->getName() . 
				', ' . $to->getClass()->getName() . ' instance given.') ;
		}
		$this->array[] = $to ;
		return true ;
	}

	public function contains(\temple\TempleObject $to) {
		return $this->indexOf($to) >= 0 ;
	}

	public function remove(\temple\TempleObject $to) {
		$i = $this->indexOf($to) ;
		$b = $i >= 0 ;
		if($b) {
			unset($this->array[$i]) ;
		}
		return $b ;
	}
	
	private function indexOf(\temple\TempleObject $to) {
		$i = -1 ;
		if($this->class->isInstance($to)) {
			foreach ($this->array as $k => $v) {
				if($v->equals($to)) {
					$i = $k ;
					break;
				}
			}
		}
		return $i ;
	}

	public final function count() {
		return count($this->array) ;
	}

	public final function current() {
		return current($this->array) ;
	}

	public final function key() {
		return key($this->array) ;
	}

	public final function next() {
		next($this->array) ;
	}

	public final function rewind() {
		reset($this->array) ;
	}

	public final function valid() {
		return key($this->array) !== null ;
	}
	
}
