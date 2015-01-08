<?php

namespace temple\_generated\chelseamai\model ;

/**
 * Generated class, dot not modify
 */
class AddressProxy extends \chelseamai\model\Address {

	use \temple\data\persistence\model\ModelProxy ;
	
	// Overridden methods
	public function getCustomer() {
		$this->lazyLoad() ;
		return $this->instance->getCustomer();
	}
	public function getFirstname() {
		$this->lazyLoad() ;
		return $this->instance->getFirstname();
	}
	public function getLastname() {
		$this->lazyLoad() ;
		return $this->instance->getLastname();
	}
	public function getCompany() {
		$this->lazyLoad() ;
		return $this->instance->getCompany();
	}
	public function getStreet() {
		$this->lazyLoad() ;
		return $this->instance->getStreet();
	}
	public function getComp() {
		$this->lazyLoad() ;
		return $this->instance->getComp();
	}
	public function getCity() {
		$this->lazyLoad() ;
		return $this->instance->getCity();
	}
	public function getState() {
		$this->lazyLoad() ;
		return $this->instance->getState();
	}
	public function getPostcode() {
		$this->lazyLoad() ;
		return $this->instance->getPostcode();
	}
	public function getPhone() {
		$this->lazyLoad() ;
		return $this->instance->getPhone();
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