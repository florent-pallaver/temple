<?php

namespace temple\web\html\bootstrap;

use temple\web\html\bootstrap\ComponentFactory as CF;
use temple\web\html\HTMLElement;
use temple\web\html\bootstrap\CssVariant as CV;

/**
 * Description of Panel
 *
 * @author florent
 */
class Panel extends AbstractComponent {

	/**
	 * @var CssVariant
	 */
	public static $DEFAULT_VARIANT ;
	
	/**
	 * 
	 * @param mixed $title
	 * @param HTMLElement $body
	 * @param HTMLElement $footer
	 * @param CssVariant $variant
	 * @param Accordion $a
	 * @param boolean $shown
	 */
	public function __construct($title, HTMLElement $body = null, HTMLElement $footer = null, CV $variant = null, Accordion $a = null, $shown = true) {
		parent::__construct('div');
		$this->addCompositeCssClass('panel', $variant ? $variant : self::$DEFAULT_VARIANT);
		if($a) {
			$parent = CF::createDiv('panel-collapse collapse')->addCssClass($shown ? 'in' : null);
			$t = AnchorLink::create0($parent, $title)
				->setData(['toggle'=>'collapse', 'parent'=>'#'.$a->getId()]);
		} else {
			$parent = $this ;
			$t = CF::toHTMLElement($title) ;
		}
		if ($a || $t) {
			$this->addChild(CF::createComponent('div', 'panel-heading', CF::createComponent('h4', 'panel-title', $t)));
		}
		if($a) {
			$this->addChild($parent) ;
		}
		if($body) {
			$parent->addChild(CF::createComponent('div', 'panel-body', $body));
		}
		if ($footer) {
			$parent->addChild(CF::createComponent('div', 'panel-footer', $footer));
		}
	}

	/**
	 * 
	 * @param type $icon
	 * @param type $title
	 * @param \temple\web\html\HTMLElement $body
	 * @param \temple\web\html\HTMLElement $footer
	 * @param \temple\web\html\bootstrap\CssVariant $variant
	 * @return \temple\web\html\bootstrap\Panel
	 */
	public static function create($icon, $title, HTMLElement $body = null, HTMLElement $footer = null, CV $variant = null) {
		return new Panel($icon || $title ? \temple\web\html\IconFactory::getInstance()->createText($icon, $title) : null, $body, $footer, $variant);
	}

	/**
	 * 
	 * @param \temple\web\html\HTMLElement $body
	 * @param \temple\web\html\bootstrap\CssVariant $variant
	 * @return \temple\web\html\bootstrap\Panel
	 */
	public static function createBodyPanel(HTMLElement $body, CV $variant = null) {
		return Panel::create(null, null, $body, null, $variant);
	}

	/**
	 * 
	 * @param type $icon
	 * @param type $title
	 * @param \temple\web\html\bootstrap\CssVariant $variant
	 * @return type
	 */
	public static function createEmpty($icon, $title, CV $variant = null) {
		return self::create($icon, $title, null, null, $variant) ;
	}
	
	private static function _init() {
		self::$DEFAULT_VARIANT = CV::$DEFAULT ;
	}
	
}
