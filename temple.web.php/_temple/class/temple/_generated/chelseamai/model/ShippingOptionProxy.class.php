<?php

namespace temple\_generated\chelseamai\model ;

/**
 * Generated class, dot not modify
 */
class ShippingOptionProxy extends \chelseamai\model\ShippingOption {

	use \temple\data\persistence\model\ModelProxy ;
	
	// Overridden methods
	public function getName() {
		$this->lazyLoad() ;
		return $this->instance->getName();
	}
	public function getPrice() {
		$this->lazyLoad() ;
		return $this->instance->getPrice();
	}
	public function getShort() {
		$this->lazyLoad() ;
		return $this->instance->getShort();
	}
	public function getFull() {
		$this->lazyLoad() ;
		return $this->instance->getFull();
	}
	public function isActive() {
		$this->lazyLoad() ;
		return $this->instance->isActive();
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