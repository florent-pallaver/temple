<?php

namespace temple\web\html\bootstrap;

use temple\web\html\bootstrap\ComponentFactory as CF;
use temple\web\html\HTMLElement ;

/**
 * Description of Panel
 *
 * @author florent
 */
class Panel extends AbstractComponent {

	public function __construct($icon, $title, HTMLElement $body, HTMLElement $footer = null, CssVariant $variant = null) {
		parent::__construct('div');
		$this->addCompositeCssClass('panel', $variant ? $variant : CssVariant::$DEFAULT)
				->addChild(CF::createComponent('div', 'panel-heading')
								->addChild(CF::createComponent('h4', 'panel-title')
												->addChild(new InnerText($icon, $title))))
				->addChild(CF::createComponent('div', 'panel-body')->addChild($body));
		if($footer) {
			$this->addChild(CF::createComponent('div', 'panel-footer')->addChild($footer));
		}
	}

}

