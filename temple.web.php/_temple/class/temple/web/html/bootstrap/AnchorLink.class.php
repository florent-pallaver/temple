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

	// href is set when constructed in case the linked element is rendered after this one.
	public function __construct(HTMLNode $toNode, HTMLElement $e, $cssClass = null) {
		parent::__construct('#', $e, $cssClass);
		$toNode->addRenderListener($this) ;
		$this->setAttribute('href', '#' . $toNode->getId()) ;
	}
	
	// we know it is a node here cause the constructor take a node as argument
	public function preRender(HTMLElement $rendered) {
		$this->setAttribute('href', '#' . $rendered->getId()) ;
	}
	
	/**
	 * 
	 * @param \temple\web\html\HTMLNode $toNode
	 * @param type $content
	 * @param type $cssClass
	 * @return \temple\web\html\bootstrap\AnchorLink
	 */
	public static function create0(HTMLNode $toNode, $content, $cssClass = null) {
		return new AnchorLink($toNode, ComponentFactory::toHTMLElement($content), $cssClass) ;
	}
	
}
