<?php

namespace temple\_generated\chelseamai\model ;

/**
 * Generated class, dot not modify
 */
class EmailTemplateProxy extends \chelseamai\model\EmailTemplate {

	use \temple\data\persistence\model\ModelProxy ;
	
	// Overridden methods
	public function getSubject() {
		$this->lazyLoad() ;
		return $this->instance->getSubject();
	}
	public function getContent() {
		$this->lazyLoad() ;
		return $this->instance->getContent();
	}
	public function getLastModified() {
		$this->lazyLoad() ;
		return $this->instance->getLastModified();
	}
	public function setSubject($subject) {
		$this->lazyLoad() ;
		return $this->instance->setSubject($subject);
	}
	public function setContent($content) {
		$this->lazyLoad() ;
		return $this->instance->setContent($content);
	}
	public function setLastModified($lastModified) {
		$this->lazyLoad() ;
		return $this->instance->setLastModified($lastModified);
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