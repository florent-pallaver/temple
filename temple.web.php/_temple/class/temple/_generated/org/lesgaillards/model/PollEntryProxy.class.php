<?php

namespace temple\_generated\org\lesgaillards\model ;

/**
 * Generated class, dot not modify
 */
class PollEntryProxy extends \org\lesgaillards\model\PollEntry {

	use \temple\data\persistence\model\ModelProxy ;
	
	// Overridden methods
	public function getFirstname() {
		$this->lazyLoad() ;
		return $this->instance->getFirstname();
	}
	public function getName() {
		$this->lazyLoad() ;
		return $this->instance->getName();
	}
	public function jsonSerialize() {
		$this->lazyLoad() ;
		return $this->instance->jsonSerialize();
	}
	public function getClass() {
		$this->lazyLoad() ;
		return $this->instance->getClass();
	}
	public function getId() {
		$this->lazyLoad() ;
		return $this->instance->getId();
	}

}			