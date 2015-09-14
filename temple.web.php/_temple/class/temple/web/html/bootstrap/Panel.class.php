<?php

namespace temple\web\html\bootstrap;

use temple\web\html\bootstrap\ComponentFactory as CF;
use temple\web\html\HTMLElement;
use temple\web\html\bootstrap\CssVariant;

/**
 * Description of Panel
 *
 * @author florent
 */
class Panel extends AbstractComponent {

	public function __construct($title, HTMLElement $body = null, HTMLElement $footer = null, CssVariant $variant = null, Accordion $a = null, $shown = true) {
		parent::__construct('div');
		$this->addCompositeCssClass('panel', $variant ? $variant : CssVariant::$DEFAULT);
		if($a) {
			$parent = CF::createDiv('panel-collapse collapse')->addCssClass($shown ? 'in' : null);
			$t = AnchorLink::create0($parent, $title)
				->setData(['toggle'=>'collapse', 'parent'=>'#'.$a->getId()]);
		} else {
			$parent = $this ;
			$t = CF::toHTMLElement($title) ;
		}
		if ($a || $t) {
			$this->addChild(CF::createComponent('div', 'panel-heading')
							->addChild(CF::createComponent('h4', 'panel-title')
									->addChild($t)));
		}
		if($a) {
			$this->addChild($parent) ;
		}
		if($body) {
			$parent->addChild(CF::createComponent('div', 'panel-body')->addChild($body));
		}
		if ($footer) {
			$parent->addChild(CF::createComponent('div', 'panel-footer')->addChild($footer));
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
	public static function create($icon, $title, HTMLElement $body = null, HTMLElement $footer = null, CssVariant $variant = null) {
		return new Panel($icon || $title ? new InnerText($icon, $title) : null, $body, $footer, $variant);
	}

	/**
	 * 
	 * @param \temple\web\html\HTMLElement $body
	 * @param \temple\web\html\bootstrap\CssVariant $variant
	 * @return \temple\web\html\bootstrap\Panel
	 */
	public static function createBodyPanel(HTMLElement $body, CssVariant $variant = null) {
		return Panel::create(null, null, $body, null, $variant);
	}

	/**
	 * 
	 * @param type $icon
	 * @param type $title
	 * @param \temple\web\html\bootstrap\CssVariant $variant
	 * @return type
	 */
	public static function createEmpty($icon, $title, CssVariant $variant = null) {
		return self::create($icon, $title, null, null, $variant) ;
	}
	
}
