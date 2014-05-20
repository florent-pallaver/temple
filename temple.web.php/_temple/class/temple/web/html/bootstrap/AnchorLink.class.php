<?php

namespace temple\web\html\bootstrap;

use temple\web\html\HTMLNode ;
use temple\web\html\HTMLElement ;

/**
 * Description of AnchorLink
 *
 * @author florent
 */
class AnchorLink extends Link implements \temple\web\html\RenderObserver {

	public function __construct(HTMLNode $toNode, $icon, $cssClass = null) {
		parent::__construct('#', new InnerText($icon), $cssClass);
		$toNode->addRenderListener($this) ;
	}
	
	// we know it is a node here cause the constructor take a node as argument
	public function preRender(HTMLElement $rendered) {
		$this->setAttribute('href', '#' . $rendered->getId()) ;
	}
	
}
