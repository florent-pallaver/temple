<?php

namespace temple\web\html\bootstrap;

use temple\web\html\bootstrap\ComponentFactory as CF;
use temple\web\html\HTMLNode as HN;
use temple\web\html\HTMLElement ;

/**
 * Description of Modal
 *
 * @author florent
 */
class Modal extends AbstractComponent {

	/**
	 * @var HN
	 */
	private static $close;

	public function __construct($title, HTMLElement $body, HTMLElement $footer = null, $cssClass = '', $modalTitleSize = 4) {
		parent::__construct('div', 'modal fade');
		$mf = $footer ? CF::createComponent('div', 'modal-footer')->addChild($footer): null ;
		$this->addChild(CF::createComponent('div', 'modal-dialog')
						->addCssClass($cssClass)
						->addChild(CF::createComponent('div', 'modal-content')
								->addChild(CF::createComponent('div', 'modal-header')
										->addChild(self::$close)
										->addChild(CF::createComponent('h' . $modalTitleSize, 'modal-title', $title)))
								->addChild(CF::createComponent('div', 'modal-body')
										->addChild($body))
								->addChild($mf)));
	}

	/**
	 * 
	 * @param \temple\web\html\HTMLElement $e
	 * @param type $cssClass
	 * @param \temple\web\html\bootstrap\CssVariant $variant
	 * @return AnchorLink
	 */
	public function createLink(HTMLElement $e, $cssClass = 'btn', CssVariant $variant = null) {
		$l = new AnchorLink($this, $e) ;
		$v = _dif($variant, CssVariant::$DEFAULT) ;
		return $l->setData(['toggle'=>'modal'])->addCompositeCssClass($cssClass, $v) ;
	}
	
	private static function _init() {
		self::$close = new HN('button', ['type' => 'button', 'class' => 'close', 'aria-hidden' => 'true'], ['dismiss' => 'modal']);
		self::$close->addChild(new \temple\web\html\HTMLString('&times', true));
	}

}

