<?php

namespace temple\_generated\chelseamai\model ;

/**
 * Generated class, dot not modify
 */
class CategoryProxy extends \chelseamai\model\Category {

	use \temple\data\persistence\model\ModelProxy ;
	
	// Overridden methods
	public function getChildCount() {
		$this->lazyLoad() ;
		return $this->instance->getChildCount();
	}
	public function getName() {
		$this->lazyLoad() ;
		return $this->instance->getName();
	}
	public function getCategoryId() {
		$this->lazyLoad() ;
		return $this->instance->getCategoryId();
	}
	public function jsonSerialize() {
		$this->lazyLoad() ;
		return $this->instance->jsonSerialize();
	}
	public function __toString() {
		$this->lazyLoad() ;
		return $this->instance->__toString();
	}

}			