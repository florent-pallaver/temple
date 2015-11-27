<?php

namespace temple\web\html\bootstrap;

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
	 * @param mixed $item
	 * @param string $cssClass
	 * @return Dropdown
	 */
	public function addItem($item = null, $cssClass = null) {
		$this->getItemList()->addItem($item, $cssClass);
		return $this;
	}

	/**
	 * 
	 * @return Dropdown
	 */
	public function addDivider() {
		$this->getItemList()->addDivider();
		return $this;
	}

	/**
	 * 
	 * @param type $header
	 * @return Dropdown
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
			$child = ItemList::createUnordered('dropdown-menu')->setAria(['labelledby' => $this->getChild(0)->getId()]);
			$this->addChild($child, self::LIST_KEY);
		}
		return $child;
	}

	/**
	 * 
	 * @param string $icon
	 * @param mixed $text
	 * @param \temple\web\html\bootstrap\CssVariant $variant
	 * @param string $cssClass
	 * @return Dropdown
	 */
	public static function createButton($icon, $text, CssVariant $variant = null, $cssClass = null) {
		$d = new Dropdown('div', $cssClass);
		return $d->addCssClass('btn-group')
						->addChild(Button::create([\temple\web\html\IconFactory::getInstance()->createText($icon, $text), ComponentFactory::$CARET], $variant)
								->addCssClass('dropdown-toggle')
								->setData(['toggle' => 'dropdown'])->setAria(['haspopup'=>'true', 'expanded'=>'false']));
	}

	/**
	 * 
	 * @param mixed $content
	 * @return Dropdown
	 */
	public static function createNavLink($content) {
		$d = new Dropdown('li', 'dropdown') ;
		return $d->addChild(Link::create(null, [$content, ComponentFactory::$CARET], 'dropdown-toggle')
								->setData(['toggle' => 'dropdown'])->setAria(['haspopup'=>'true', 'expanded'=>'false']));
	}

}
