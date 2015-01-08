<?php

namespace temple\_generated\chelseamai\model ;

/**
 * Generated class, dot not modify
 */
class PartnerProxy extends \chelseamai\model\Partner {

	use \temple\data\persistence\model\ModelProxy ;
	
	// Overridden methods
	public function getCouponCode() {
		$this->lazyLoad() ;
		return $this->instance->getCouponCode();
	}
	public function getParent() {
		$this->lazyLoad() ;
		return $this->instance->getParent();
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