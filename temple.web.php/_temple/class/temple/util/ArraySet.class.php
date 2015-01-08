<?php

namespace temple\util;

/**
 * Description of ArraySet
 *
 * @author florent
 */
class ArraySet extends AbstractCollection {

	/**
	 * 
	 * @param \ReflectionClass $class
	 */
	public function __construct(\ReflectionClass $class) {
		parent::__construct($class);
	}
	
	public function add(\temple\TempleObject $to) {
		return (!$this->contains($to)) && parent::add($to) ;
	}

}
