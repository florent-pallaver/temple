<?php

namespace temple\web\html\bootstrap;

use temple\web\html\HTMLNodeFactory as NF ;

/**
 * Description of ComponentFactory
 *
 * @author florent
 */
abstract class ComponentFactory {

	private function __construct() {
		;
	}	

	/**
	 * @var \temple\web\html\HTMLNode
	 */
	public static $CARET ;
	
	/**
	 * @var \temple\web\html\HTMLNode
	 */
	public static $CARET_BUTTON ;
	
	/**
	 * 
	 * @param type $nodeName
	 * @param type $cssClass
	 * @return \temple\web\html\HTMLNode
	 */
	public static function createComponent($nodeName, $cssClass = null) {
		return NF::createNodeWithClass($nodeName, $cssClass) ;
	}
	
	/**
	 * 
	 * @param type $label
	 * @param type $cssClass
	 * @return \temple\web\html\HTMLNode
	 */
	public static function createLabel($label, $cssClass) {
		return NF::createNodeWithClassAndString('label', $cssClass, $label) ;
	}
	
	private static function _init() {
		self::$CARET = NF::createNodeWithClass('span', 'caret');
		self::$CARET_BUTTON = NF::createNode('button', ['class'=>'btn btn-primary dropdown-toggle'], ['toggle'=>'dropdown'])->addChild(self::$CARET) ;
	}
	
}
