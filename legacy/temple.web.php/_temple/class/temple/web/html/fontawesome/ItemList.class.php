<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace temple\web\html\fontawesome;

/**
 * Description of ItemList
 *
 * @author flominou
 */
class ItemList extends \temple\web\html\AbstractFrameworkComponent {

	public function __construct() {
		parent::__construct('ul');
		$this->addCssClass('fa-ul') ;
	}
	
	/**
	 * 
	 * @param type $icon
	 * @param type $content
	 * @param array $iv
	 * @param type $cssClass
	 * @return ItemList
	 */
	public function addItem($icon, $content, $iv = [], $cssClass = null) {
		$li = new \temple\web\html\HTMLNode('li') ;
		$iv[] = IconVariation::$LI ;
		$li->addCssClass($cssClass)
				->addChild($this->createIcon($icon, $iv))
				->addChild($this->toHTMLElement($content)) ;
		return $this->addChild($li);
	}
	
	public function addIconable(\temple\util\Iconable $i, $iv = [], $cssClass = null) {
		return $this->addItem($i->getIcon(), $i->__toString(), $iv, $cssClass) ;
	}

	/**
	 * 
	 * @return \temple\web\html\fontawesome\ItemList
	 */
	public static function create() {
		return new ItemList() ;
	}
	
}
