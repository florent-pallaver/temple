<?php

namespace temple\web\html\bootstrap;

use temple\web\html\HTMLElement as HE;

/**
 * Description of Dropdown
 *
 * @author florent
 */
class Dropdown extends AbstractComponent {

	const LIST_KEY = '_list|';

	/**
	 * Adds an item to this dropdown
	 * 
	 * @param \temple\web\html\HTMLElement $item
	 * @param string $cssClass
	 * @return AbstractDropdown
	 */
	public function addItem(HE $item = null, $cssClass = null) {
		$this->getItemList()->addItem($item, $cssClass);
		return $this;
	}

	/**
	 * 
	 * @return AbstractDropdown
	 */
	public function addDivider() {
		$this->getItemList()->addDivider();
		return $this;
	}

	/**
	 * 
	 * @param type $header
	 * @return AbstractDropdown
	 */
	public function addHeader($header) {
		return $this->addItem($this->toHTMLElement($header), 'dropdown-header');
	}

	/**
	 * @return ItemList
	 */
	private function getItemList() {
		$child = $this->getChild(self::LIST_KEY);
		if (!$child) {
			$child = ItemList::createUnordered('dropdown-menu') ;
			$this->addChild($child, self::LIST_KEY);
		}
		return $child;
	}

	/**
	 * 
	 * @param type $icon
	 * @param type $text
	 * @param \temple\web\html\bootstrap\CssVariant $variant
	 * @param type $cssClass
	 * @return Dropdown
	 */
	public static function createButton($icon, $text, CssVariant $variant = null, $cssClass = null) {
		$d = new Dropdown('div', $cssClass);
		return $d->addCssClass('btn-group')
						->addChild(Button::create([\temple\web\html\IconFactory::getInstance()->createText($icon, $text), ComponentFactory::$CARET], $variant)
								->addCssClass('dropdown-toggle')
								->setData(['toggle' => 'dropdown']));
	}

	/**
	 * 
	 * @param type $icon
	 * @param type $text
	 * @return Dropdown
	 */
	public static function createNavLink($icon, $text) {
		$d = new Dropdown('li', 'dropdown');
		return $d->addChild(Link::create('#', [\temple\web\html\IconFactory::getInstance()->createText($icon, $text), ComponentFactory::$CARET], 'dropdown-toggle')
								->setData(['toggle' => 'dropdown']));
	}

}
