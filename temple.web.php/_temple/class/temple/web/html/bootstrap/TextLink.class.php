<?php

namespace temple\web\html\bootstrap;

/**
 * Description of TextLink
 *
 * @author florent
 */
final class TextLink extends Link {

	/**
	 * 
	 * @param type $href
	 * @param type $icon
	 * @param type $text
	 * @param type $cssClass
	 */
	public function __construct($href, $icon, $text, $cssClass = null) {
		parent::__construct($href, null, $cssClass) ;
		$this->addChild(new InnerText($icon, $text)) ;
	}

}
