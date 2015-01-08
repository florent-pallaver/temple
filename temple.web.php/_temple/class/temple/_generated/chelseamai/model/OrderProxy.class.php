<?php

namespace temple\_generated\chelseamai\model ;

/**
 * Generated class, dot not modify
 */
class OrderProxy extends \chelseamai\model\Order {

	use \temple\data\persistence\model\ModelProxy ;
	
	// Overridden methods
	public function getReference() {
		$this->lazyLoad() ;
		return $this->instance->getReference();
	}
	public function getCustomer() {
		$this->lazyLoad() ;
		return $this->instance->getCustomer();
	}
	public function setCustomer($customer) {
		$this->lazyLoad() ;
		return $this->instance->setCustomer($customer);
	}
	public function getCreationDate() {
		$this->lazyLoad() ;
		return $this->instance->getCreationDate();
	}
	public function getStatus() {
		$this->lazyLoad() ;
		return $this->instance->getStatus();
	}
	public function setStatus($status) {
		$this->lazyLoad() ;
		return $this->instance->setStatus($status);
	}
	public function getItems() {
		$this->lazyLoad() ;
		return $this->instance->getItems();
	}
	public function getItemsCount() {
		$this->lazyLoad() ;
		return $this->instance->getItemsCount();
	}
	public function getProductQuantity($p) {
		$this->lazyLoad() ;
		return $this->instance->getProductQuantity($p);
	}
	public function addProduct($p) {
		$this->lazyLoad() ;
		return $this->instance->addProduct($p);
	}
	public function setProductQuantity($p, $quantity) {
		$this->lazyLoad() ;
		return $this->instance->setProductQuantity($p, $quantity);
	}
	public function getPrice() {
		$this->lazyLoad() ;
		return $this->instance->getPrice();
	}
	public function getTotalPrice() {
		$this->lazyLoad() ;
		return $this->instance->getTotalPrice();
	}
	public function getBillingAddress() {
		$this->lazyLoad() ;
		return $this->instance->getBillingAddress();
	}
	public function getShippingAddress() {
		$this->lazyLoad() ;
		return $this->instance->getShippingAddress();
	}
	public function setBillingAddress($billingAddress) {
		$this->lazyLoad() ;
		return $this->instance->setBillingAddress($billingAddress);
	}
	public function setShippingAddress($shippingAddress) {
		$this->lazyLoad() ;
		return $this->instance->setShippingAddress($shippingAddress);
	}
	public function getShippingOption() {
		$this->lazyLoad() ;
		return $this->instance->getShippingOption();
	}
	public function setShippingOption($shippingOption) {
		$this->lazyLoad() ;
		return $this->instance->setShippingOption($shippingOption);
	}
	public function getComment() {
		$this->lazyLoad() ;
		return $this->instance->getComment();
	}
	public function setComment($comment) {
		$this->lazyLoad() ;
		return $this->instance->setComment($comment);
	}
	public function getTracking() {
		$this->lazyLoad() ;
		return $this->instance->getTracking();
	}
	public function setTracking($tracking) {
		$this->lazyLoad() ;
		return $this->instance->setTracking($tracking);
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