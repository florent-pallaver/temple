<?php

namespace temple\_generated\chelseamai\model ;

/**
 * Generated class, dot not modify
 */
class CustomerProxy extends \chelseamai\model\Customer {

	use \temple\data\persistence\model\ModelProxy ;
	
	// Overridden methods
	public function getName() {
		$this->lazyLoad() ;
		return $this->instance->getName();
	}
	public function getEmail() {
		$this->lazyLoad() ;
		return $this->instance->getEmail();
	}
	public function getHash() {
		$this->lazyLoad() ;
		return $this->instance->getHash();
	}
	public function getNewsletter() {
		$this->lazyLoad() ;
		return $this->instance->getNewsletter();
	}
	public function getCreationDate() {
		$this->lazyLoad() ;
		return $this->instance->getCreationDate();
	}
	public function getPartner() {
		$this->lazyLoad() ;
		return $this->instance->getPartner();
	}
	public function getToken() {
		$this->lazyLoad() ;
		return $this->instance->getToken();
	}
	public function setEmail($email) {
		$this->lazyLoad() ;
		return $this->instance->setEmail($email);
	}
	public function setHash($password) {
		$this->lazyLoad() ;
		return $this->instance->setHash($password);
	}
	public function setNewsletter($newsletter) {
		$this->lazyLoad() ;
		return $this->instance->setNewsletter($newsletter);
	}
	public function setAddress($address) {
		$this->lazyLoad() ;
		return $this->instance->setAddress($address);
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