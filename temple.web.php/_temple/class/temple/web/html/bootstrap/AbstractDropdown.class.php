<?php

namespace temple\web\html\bootstrap;

use temple\web\html\HTMLElement as HE ;

/**
 * Description of AbstractDropdown
 *
 * @author florent
 */
abstract class AbstractDropdown extends AbstractGroup {

	const LIST_KEY = '_list|' ;
	
	public function __construct(CssVariant $grpVariant = null, $cssClass = null) {
		parent::__construct('btn', $grpVariant, $cssClass);
	}
	
	/**
	 * Adds an item to this dropdown
	 * 
	 * @param \temple\web\html\HTMLElement $child
	 * @param string $cssClass
	 * @return \temple\web\html\bootstrap\AbstractDropdown
	 */
	public function addItem(HE $item = null, $cssClass = null) {
		$this->getItemList()->addItem($item, $cssClass) ;
		return $this ;
	}
	
	/**
	 * 
	 * @return \temple\web\html\bootstrap\AbstractDropdown
	 */
	public function addDivider() {
		$this->getItemList()->addDivider() ;
		return $this ;
	}
	
	/**
	 * @return ItemList
	 */
	private function getItemList() {
		$child = $this->getChild(self::LIST_KEY) ;
		if(!$child) {
			$child = $this->addNode(new ItemList(false, 'dropdown-menu'), self::LIST_KEY) ;
		}
		return $child ;
	}
	
}
