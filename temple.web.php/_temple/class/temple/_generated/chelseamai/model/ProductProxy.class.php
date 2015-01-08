<?php

namespace temple\_generated\chelseamai\model ;

/**
 * Generated class, dot not modify
 */
class ProductProxy extends \chelseamai\model\Product {

	use \temple\data\persistence\model\ModelProxy ;
	
	// Overridden methods
	public function getDescription() {
		$this->lazyLoad() ;
		return $this->instance->getDescription();
	}
	public function getPrice() {
		$this->lazyLoad() ;
		return $this->instance->getPrice();
	}
	public function getStock() {
		$this->lazyLoad() ;
		return $this->instance->getStock();
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