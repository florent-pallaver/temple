<?php

namespace temple\web\html\bootstrap\descriptor ;

/**
 * Description of ListItemLinkListDescriptor
 *
 * @author florent
 */
final class ListItemLinkListDescriptor extends AbstractListDescriptor {
	
	public $cssClass = null ;
	
	public function add($href, $icon, $text, $active = false) {
		$this->addDescriptor(new ListItemLinkDescriptor($href, $icon, $text, $active)) ;
	}
	
	protected function getListComponent() {
		return new \temple\web\html\bootstrap\UnorderedList($this->cssClass) ;
	}
	
}
