<?php

namespace temple\web\html\bootstrap\descriptor ;

/**
 * Description of ListItemDescriptor
 *
 * @author florent
 */
class ListItemDescriptor implements ComponentDescriptor {

	public $icon ;
	
	public $text ;
	
	public function __construct($icon = null, $text = null) {
		$this->icon = $icon;
		$this->text = $text;
	}

	public function toComponent() {
		return new \temple\web\html\bootstrap\ListItem($this->icon, $this->text) ;
	}
	
}
