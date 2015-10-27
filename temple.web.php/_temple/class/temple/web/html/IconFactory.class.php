<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace temple\web\html;

/**
 * Description of IconFactory
 *
 * @author flominou
 */
abstract class IconFactory {
	
	use \temple\ToImplement ;
	
	const FONTAWESOME_IMPL = 'fontawesome' ;
	
	const BOOTSTRAP_IMPL = 'bootstrap' ;

	/**
	 * TODOC
	 * @param string $name
	 * @param array $variations array of IconVariation 
	 * @return FrameworkComponent or null if $anme is null
	 * @see IconVariation
	 */
	public final function createIcon($name, array $variations = []) {
		return $name ? $this->newIcon($name, $variations)->setAttribute('aria-hidden', 'true') : null;
	}
	
	/**
	 * TODOC
	 * @param string $name
	 * @param array $variations array of IconVariation 
	 * @return FrameworkComponent
	 * @see IconVariation
	 */
	protected abstract function newIcon($name, array $variations = []) ;
	
	/**
	 * 
	 * @param string $name
	 * @param string $text
	 * @param array $variations array of IconVariation
	 * @param boolean $formatted
	 * @return HTMLElement
	 * @see IconVariation
	 */
	public function createText($name, $text, array $variations = [], $formatted = false) {
		$i = $this->createIcon($name, $variations)  ;
		$t = $text 
				? HTMLUtil::toHTMLElement([$i, new HTMLString($text, $formatted)]) 
				: $i ;
		return $t ;
	}
	
	/**
	 * @return array
	 */
	public abstract function getAlertIcons() ;
	
	public static function config($impl) {
		self::$SUB_NAMESPACE = _dif($impl, self::FONTAWESOME_IMPL) ;
	}
	
	private static function _init() {
		self::$SUB_NAMESPACE = self::FONTAWESOME_IMPL ;
	}
	
}
