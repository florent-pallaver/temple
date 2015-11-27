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

	private static $defaultLabelSpanCssClass = null ;

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
				? new HTMLElementList([$i, HTMLNodeFactory::createNode('span')
						->addChild(new HTMLString($text, $formatted))
						->addCssClass(self::$defaultLabelSpanCssClass)]) 
				: $i ;
		return $t ;
	}
	
	/**
	 * 
	 * @param \temple\util\Iconable $i
	 * @param array $variations
	 * @return HTMLElement
	 */
	public function createLabel(\temple\util\Iconable $i, array $variations = []) {
		return $this->createText($i->getIcon(), $i instanceof \temple\util\Nameable ? $i->getName() : $i, $variations) ;
	}
	
	/**
	 * @return array
	 */
	public abstract function getAlertIcons() ;
	
		/**
	 * @param string $cssClass
	 */
	public static final function resetDefaultLabelSpanCssClass($cssClass = null) {
		self::$defaultLabelSpanCssClass = $cssClass ;
	}
	
	public static function config($impl) {
		self::$SUB_NAMESPACE = _dif($impl, self::FONTAWESOME_IMPL) ;
	}
	
	private static function _init() {
		self::$SUB_NAMESPACE = self::FONTAWESOME_IMPL ;
	}
	
}
