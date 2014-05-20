<?php

namespace temple\web\html\bootstrap\descriptor;

/**
 * Description of AbstractListDescriptor
 *
 * @author florent
 */
abstract class AbstractListDescriptor implements ComponentDescriptor, \Iterator {
	
	private $descriptors = [] ;
	
	private $i = 0 ;
	
	protected final function addDescriptor(ListItemDescriptor $desc) {
		$this->descriptors[] = $desc ;
	}
	
	public final function toComponent() {
		$list = $this->getListComponent() ;
		foreach($this->descriptors as $desc) {
			$list->addChild($desc->toComponent()) ;
		}
		return $list ;
	}
	
	/**
	 * @return \temple\web\html\bootstrap\AbstractList
	 */
	protected abstract function getListComponent() ;
	
	public final function current() {
		return $this->descriptors ? $this->descriptors[$this->i] : null ;
	}

	public final function key() {
		return $this->i ;
	}

	public final function next() {
		$this->i++ ;
	}

	public final function rewind() {
		$this->i = 0 ;
	}

	public final function valid() {
		return isset($this->descriptors[$i]) ;
	}
	
}

