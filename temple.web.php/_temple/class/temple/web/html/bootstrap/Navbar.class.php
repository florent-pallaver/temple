<?php

namespace temple\web\html\bootstrap;

use temple\web\html\bootstrap\ComponentFactory as CF ;
use temple\web\html\HTMLElement;
use temple\web\html\HTMLNode;

/**
 * Description of Navbar
 *
 * @author florent
 */
class Navbar extends AbstractComponent {

	private static $headerKey = '_header|' ;
	
	private static $collaspseKey = '_collapse|' ;
	
	/**
	 * @var HTMLNode
	 */
	private $header ;
	
	/**
	 * @var HTMLNode
	 */
	private $collapse ;
	
	public function __construct(HTMLElement $brand, array $variants = [], $cssClass = null) {
		parent::__construct('nav', 'navbar');
		$this->addCssClass($cssClass)->setAttribute('role', 'navigation') ;
		foreach($variants as $var) {
			$this->addCssClass($var);
		}
		$this->header = CF::createComponent('div', 'navbar-header') ;
		$this->collapse = CF::createComponent('div', 'collapse navbar-collapse') ;
		$this
			->addChild($this->header->addChild(new Link('/', $brand, 'navbar-brand')))
			->addChild($this->collapse)
			;
	}

	/**
	 * 
	 * @return HTMLNode
	 */
	public function getToggleDiv() {
		return $this->collapse ;
	}
	
	/**
	 * 
	 * @return \temple\web\html\bootstrap\Navbar
	 */
	public function addToggleButton($menuIcon = 'menu-hamburger', CssVariant $variant = null) {
		$this->header->addChild(Button::create([$this->createIcon($menuIcon), $this->createHTMLNode('span', 'sr-only', 'Toggle Navigation')], $variant)
					->addCssClass('navbar-toggle')
					->setData(['toggle'=>'collapse', 'target'=> '#'.$this->getToggleDiv()->getId()])) ;
		return $this ;
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
