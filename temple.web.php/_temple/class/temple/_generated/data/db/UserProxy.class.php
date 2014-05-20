<?php

namespace temple\_generated\data\db ;

/**
 * Generated class, dot not modify
 */
class UserProxy extends data\db\User {

	use \temple\data\persistence\model\ModelProxy ;
	
	// Overridden methods
	public function getOwner() {
		$this->lazyLoad() ;
		return $this->instance->getOwner();
	}
	public function getName() {
		$this->lazyLoad() ;
		return $this->instance->getName();
	}
	public function getCreationDate() {
		$this->lazyLoad() ;
		return $this->instance->getCreationDate();
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