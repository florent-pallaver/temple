<?php

namespace temple\web\html\bootstrap;

use temple\web\html\HTMLElement;
use temple\web\html\HTMLNode;

/**
 * Description of Navbar
 *
 * @author florent
 */
class Navbar extends AbstractComponent {

	public function __construct(HTMLElement $brand, array $variants = []) {
		parent::__construct('nav', 'navbar');
		foreach($variants as $var) {
			$this->addCssClass($var);
		}
		$this->addComponent(new Link('/', $brand), [NavbarVariant::$BRAND]);
	}

	/**
	 * 
	 * @param \temple\web\html\HTMLNode $comp
	 * @param array $variants
	 * @return Navbar
	 */
	public function addComponent(HTMLNode $comp, array $variants = []) {
		foreach ($variants as $var) {
			$comp->addCssClass($var) ;
		}
		return $this->addChild($comp) ;
	}

}
