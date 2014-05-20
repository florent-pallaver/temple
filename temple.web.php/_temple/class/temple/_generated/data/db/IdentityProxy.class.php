<?php

namespace temple\_generated\data\db ;

/**
 * Generated class, dot not modify
 */
class IdentityProxy extends data\db\Identity {

	use \temple\data\persistence\model\ModelProxy ;
	
	// Overridden methods
	public function getCreationDate() {
		$this->lazyLoad() ;
		return $this->instance->getCreationDate();
	}
	public function getOwner() {
		$this->lazyLoad() ;
		return $this->instance->getOwner();
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