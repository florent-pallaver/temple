<?php

namespace temple\web\html\bootstrap;

/**
 * Description of ItemList
 *
 * @author florent
 */
class ItemList extends AbstractList {

	public final function addItem($item = null, $cssClass = null) {
		if ($item) {
			$this->addChild($item instanceof \temple\web\html\Node && $item->isNode('li') ?
							$item->addCssClass($cssClass) : new ListItem($this->toHTMLElement($item), $cssClass));
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
