<?php

namespace temple\_generated\chelseamai\model ;

/**
 * Generated class, dot not modify
 */
class OrderItemProxy extends \chelseamai\model\OrderItem {

	use \temple\data\persistence\model\ModelProxy ;
	
	// Overridden methods
	public function getOrder() {
		$this->lazyLoad() ;
		return $this->instance->getOrder();
	}
	public function getProduct() {
		$this->lazyLoad() ;
		return $this->instance->getProduct();
	}
	public function getProductName() {
		$this->lazyLoad() ;
		return $this->instance->getProductName();
	}
	public function getProductPrice() {
		$this->lazyLoad() ;
		return $this->instance->getProductPrice();
	}
	public function getQuantity() {
		$this->lazyLoad() ;
		return $this->instance->getQuantity();
	}
	public function getPrice() {
		$this->lazyLoad() ;
		return $this->instance->getPrice();
	}
	public function add($quantity) {
		$this->lazyLoad() ;
		return $this->instance->add($quantity);
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