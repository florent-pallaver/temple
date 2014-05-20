<?php

namespace temple\web\html\bootstrap;

/**
 * Description of LinkAndDropdown
 *
 * @author florent
 */
class LinkAndDropdown extends AbstractDropdown {

	const LINK_KEY = '_link|' ;

	const BUTTON_KEY = '_button|' ;
	
	private static $caret ;
	
	public function __construct($href, $icon, $text, CssVariant $grpVariant = null, CssVariant $btnVariant = null) {
		parent::__construct($grpVariant) ;
		$btnVariant_ = _dif($btnVariant, CssVariant::$DEFAULT) ;
		$link = new TextLink($href, $icon, $text) ;
		$this->addChild($link->addCompositeCssClass('btn', $btnVariant_), self::LINK_KEY)
				->addNode(Button::create(self::$caret, $btnVariant_), self::BUTTON_KEY)
				->setAttribute('data-toggle', 'dropdown') ;
	}
	
	private static function _init() {
		self::$caret = new InnerText(null, null, true) ;
	}
}
