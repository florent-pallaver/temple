<?php

namespace temple\web\html\bootstrap;

use temple\web\html\bootstrap\CssVariant ;

/**
 * Description of Accordion
 *
 * @author florent
 */
class Accordion extends AbstractGroup {

	public function __construct($cssClass = null) {
		parent::__construct('panel', null, $cssClass);
	}
	
	/**
	 * 
	 * @param type $title
	 * @param \temple\web\html\HTMLElement $body
	 * @param boolean $shown
	 * @return Accordion
	 */
	public function addPanel($title, \temple\web\html\HTMLElement $body, $shown = true, CssVariant $var = null) {
		return $this->addChild(new Panel($title, $body, null, $var, $this, $shown)) ;
	}
	
	/**
	 * 
	 * @return Accordion
	 */
	public static function create($cssClass = null) {
		return new Accordion($cssClass) ;
	}
	
}
