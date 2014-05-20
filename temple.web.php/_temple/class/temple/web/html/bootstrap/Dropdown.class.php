<?php

namespace temple\web\html\bootstrap;

/**
 * Description of Dropdown
 *
 * @author florent
 */
class Dropdown extends AbstractDropdown {

	public function __construct($icon, $text, CssVariant $variant = null, $cssClass = null) {
		parent::__construct(null, $cssClass);
		$this->addNode(new Button(Button::TYPE_BUTTON, new InnerText($icon, $text, true), $variant))
				->setAttribute('data-toggle', 'dropdown') ;
	}
	
}
