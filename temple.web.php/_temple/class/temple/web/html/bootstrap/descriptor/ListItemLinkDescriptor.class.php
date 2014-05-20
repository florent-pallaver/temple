<?php

namespace temple\web\html\bootstrap\descriptor ;

/**
 * Description of ListItemLinkDescriptor
 *
 * @author florent
 */
final class ListItemLinkDescriptor extends ListItemDescriptor {

	public $active ;
	
	public $href ;
	
	public function __construct($href, $icon, $text = null, $active = false) {
		parent::__construct($icon, $text) ;
		$this->href = $href;
		$this->active = $active;
	}

	public function toComponent() {
		return new \temple\web\html\bootstrap\ListItemLink($this->href, $this->icon, $this->text, $this->active) ;
	}
	
}
