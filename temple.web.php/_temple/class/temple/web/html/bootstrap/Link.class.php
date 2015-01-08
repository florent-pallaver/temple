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

	/**
	 * @param type $target
	 * @return Link
	 */
	public function setTarget($target='_blank') {
		return $this->setAttribute('target', $target) ;
	}
	
	/**
	 * 
	 * @param type $href
	 * @param type $cssClass
	 * @return \temple\web\html\bootstrap\Link
	 */
	public static function create($href, $content, $cssClass = null) {
		return new Link($href, ComponentFactory::toHTMLElement($content), $cssClass) ;
	}
	
	/**
	 * 
	 * @param type $href
	 * @param \temple\web\html\HTMLElement $e
	 * @param \temple\web\html\bootstrap\CssVariant $variant
	 * @param type $cssClass
	 * @return Link
	 */
	public static function createButton($href, HTMLElement $e = null, CssVariant $variant = null, $cssClass = null) {
		$l = new Link($href, $e) ;
		$v = _dif($variant, CssVariant::$DEFAULT) ;
		return $l->addCssClass($cssClass)->addCompositeCssClass('btn', $v) ;
	}
	
}
