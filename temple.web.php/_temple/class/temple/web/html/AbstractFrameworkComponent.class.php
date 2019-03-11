<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace temple\web\html;

/**
 * Description of AbstractFrameworkComponent
 *
 * @author flominou
 */
abstract class AbstractFrameworkComponent extends HTMLNode implements FrameworkComponent {

	protected final function createIcon($name, array $variations = []) {
		return IconFactory::getInstance()->createIcon($name, $variations) ;
	}
	
	protected final function createIconedText($name, $text, array $variations = [], $formatted = false) {
		return IconFactory::getInstance()->createText($name, $text, $variations, $formatted) ;
	}
	
	/**
	 * 
	 * @param \temple\util\Iconable $i
	 * @param array $variations
	 * @return HTMLElement
	 */
	protected final static function createIconedLabel(\temple\util\Iconable $i, array $variations = []) {
		return IconFactory::getInstance()->createLabel($i) ;
	}
	
	protected final function getAlertIcon($key) {
		return _iod(IconFactory::getInstance()->getAlertIcons(), $key) ;
	}

}
