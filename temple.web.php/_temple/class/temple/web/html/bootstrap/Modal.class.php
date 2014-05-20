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

	public function __construct($icon, $title, HTMLElement $body, HTMLElement $footer = null, $cssClass = '') {
		parent::__construct('div', 'modal fade');
		$this->addChild(CF::createComponent('div', 'modal-dialog')
						->addCssClass($cssClass)
						->addChild(CF::createComponent('div', 'modal-content')
								->addChild(CF::createComponent('div', 'modal-header')
										->addChild(self::$close)
										->addChild(CF::createComponent('h4', 'modal-title')
												->addChild(new InnerText($icon, $title))))
								->addChild(CF::createComponent('div', 'modal-body')
										->addChild($body))));
	}

	private static function _init() {
		self::$close = new HN('button', ['type' => 'button', 'class' => 'close', 'aria-hidden' => 'true'], ['dismiss' => 'modal']);
		self::$close->addChild(InnerText::create('&times', true));
	}

}

