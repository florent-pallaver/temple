<?php

namespace temple\web\html\bootstrap;

use temple\web\html\HTMLElement;

/**
 * Description of ItemList
 *
 * @author florent
 */
class ItemList extends AbstractComponent {

	/**
	 * 
	 * @param type $nodeType
	 * @param type $cssClass
	 */
	public function __construct($ordered = false, $cssClass = null) {
		parent::__construct($ordered ? 'ol' : 'ul');
		$this->addCssClass($cssClass);
	}

	/**
	 * 
	 * @param \temple\web\html\HTMLElement $item
	 * @param type $cssClass
	 * @return ItemList
	 */
	public final function addItem(HTMLElement $item = null, $cssClass = null) {
		if ($item) {
			$this->addChild($item instanceof \temple\web\html\HTMLNode && $item->isNode('li') ?
							$item->addCssClass($cssClass) : new ListItem($item, $cssClass));
		}
		return $this;
	}

	/**
	 * 
	 * @return ItemList
	 */
	public final function addDivider() {
		return $this->addChild(ListItem::$DIVIDER);
	}

	/**
	 * 
	 * @param type $cssClass
	 * @return \temple\web\html\bootstrap\ItemList
	 */
	public static function createOrdered($cssClass = null) {
		return new ItemList(true, $cssClass);
	}

	/**
	 * 
	 * @param type $cssClass
	 * @return \temple\web\html\bootstrap\ItemList
	 */
	public static function createUnordered($cssClass = null) {
		return new ItemList(false, $cssClass);
	}

}
