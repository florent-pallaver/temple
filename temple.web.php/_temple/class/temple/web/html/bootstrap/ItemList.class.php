<?php

namespace temple\web\html\bootstrap;

/**
 * Description of ItemList
 *
 * @author florent
 */
class ItemList extends AbstractComponent {

	/**
	 * Constructor 
	 * 
	 * @param boolean $ordered
	 * @param string $cssClass
	 */
	public function __construct($ordered = false, $cssClass = null) {
		parent::__construct($ordered ? 'ol' : 'ul');
		$this->addCssClass($cssClass);
	}

	/**
	 * 
	 * @param mixed $item
	 * @param string $cssClass
	 * @return ItemList
	 */
	public final function addItem($item = null, $cssClass = null) {
		if ($item) {
			$this->addChild($item instanceof \temple\web\html\Node && $item->isNode('li') ?
							$item->addCssClass($cssClass) : new ListItem($this->toHTMLElement($item), $cssClass));
		}
		return $this;
	}

	/**
	 * 
	 * @param array $items
	 * @return ItemList
	 */
	public final function addItems(array $items) {
		foreach($items as $i) {
			$this->addItem($i) ;
		}
		return $this ;
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
	 * @return ItemList
	 */
	public final function unstyled() {
		$this->addCssClass('list-unstyled') ;
		return $this ;
	}
	
	/**
	 * 
	 * @return ItemList
	 */
	public final function inline() {
		$this->addCssClass('list-inline') ;
		return $this ;
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
