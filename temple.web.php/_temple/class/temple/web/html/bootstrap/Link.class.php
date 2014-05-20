<?php

namespace temple\web\html\bootstrap;

use temple\web\html\HTMLElement ;

/**
 * Description of Link
 *
 * @author florent
 */
class Link extends AbstractComponent {

	/**
	 * 
	 * @param type $href
	 * @param type $icon
	 * @param type $text
	 * @param type $cssClass
	 */
	public function __construct($href, HTMLElement $e = null, $cssClass = null) {
		parent::__construct('a', $cssClass) ;
		$this->setAttribute('href', $href) ;
		if($e) {
			$this->addChild($e) ;
		}
	}

}
