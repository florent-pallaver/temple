<?php

namespace temple\data\persistence\model\proxy;

use temple\data\persistence\model\Key ;
use temple\data\persistence\model\Filter ;
use temple\data\persistence\model\ModelManager ;

/**
 * Description of ArrayProxy
 *
 * @author florent
 */
class ArrayProxy implements \Iterator, \ArrayAccess {

	/**
	 * @var Key
	 */
	private $foreignKey ;
	
	private $parentId ;
	
	private $elements = null;
	
	public function __construct(Key $foreignKey, $parentId) {
		$this->foreignKey = $foreignKey ;
		$this->parentId = $parentId ;
	}
	
	public function current() {
		return current($this->elements) ;
	}

	public function key() {
		return key($this->elements) ;
	}

	public function next() {
		next($this->elements) ;
	}

	public function rewind() {
		$this->lazyLoad() ;
		reset($this->elements) ;
	}

	public function valid() {
		return key($this->elements) !== null ;
	}
	
	public function offsetSet($offset, $value) {
		$this->lazyLoad() ;
        if (is_null($offset)) {
            $this->elements[] = $value;
        } else {
            $this->elements[$offset] = $value;
        }
    }
    public function offsetExists($offset) {
		$this->lazyLoad() ;
        return isset($this->elements[$offset]);
    }
    public function offsetUnset($offset) {
		$this->lazyLoad() ;
        unset($this->elements[$offset]);
    }
    public function offsetGet($offset) {
		$this->lazyLoad() ;
        return isset($this->elements[$offset]) ? $this->elements[$offset] : null;
    }
	
	private function lazyLoad() {
		if($this->elements === null) {
			$f = Filter::create($this->foreignKey->getClass())
					->addKeyCondition($this->foreignKey, $this->parentId) ;
			$this->elements = ModelManager::getInstance()->findByFilter($f) ;
		}
	}
	
}
