<?php

namespace temple\web\html ;

use temple\util\Util;

/**
 *
 * TODOC
 *
 * @author florent
 */
final class HTMLNodeFactory {

	private function __construct() {}

	/**
	 * 
	 * @param string $nodeName
	 * @param array $attributes
	 * @param array $data
	 * @return \temple\web\html\HTMLNode
	 */
	public static final function createNode($nodeName, array $attributes = [], array $data = []) {
		return new HTMLNode($nodeName, $attributes, $data) ;
	}
	
	/**
	 * TODOC
	 *
	 * @param unknown $class
	 * @return \temple\web\html\HTMLNode
	 */
	public static final function createDiv($class = null, $id = null) {
		return self::createNodeWithClass('div', $class)->setAttribute('id', $id) ;
	}
	
	/**
	 * TODOC
	 *
	 * @param unknown $nodeName
	 * @param unknown $class
	 * @return \temple\web\html\HTMLNode
	 */
	public static final function createNodeWithClass($nodeName, $class) {
		return new HTMLNode($nodeName, ['class' => $class]) ;
	}

	/**
	 * TODOC
	 *
	 * @param unknown $nodeName
	 * @param unknown $string
	 * @return \temple\web\html\HTMLNode
	 */
	public static final function createNodeWithString($nodeName, $string) {
		return (new HTMLNode($nodeName))->addChild(new HTMLString($string)) ;
	}

	/**
	 * TODOC
	 *
	 * @param unknown $nodeName
	 * @param unknown $class
	 * @param unknown $string
	 * @return \temple\web\html\HTMLNode
	 */
	public static final function createNodeWithClassAndString($nodeName, $class, $string) {
		return self::createNodeWithClass($nodeName, $class)->addChild(new HTMLString($string)) ;
	}

	/**
	 * TODOC
	 *
	 * @param string $src
	 * @param string $alt
	 * @return \temple\web\html\HTMLNode
	 */
	public static final function createImgNode($src, $alt = NULL) {
		Util::notEmptyString($src) ;
		return new HTMLNode('img', array('src' => $src, 'alt' => $alt ? $alt : $src)) ;
	}

	/**
	 * TODOC
	 *
	 * @param string $format
	 * @param string $args
	 * @return \temple\web\html\HTMLNode
	 */
	public static final function createScriptNode($format = '', array $args = array()) {
		return (new HTMLNode('script', array('type' => 'text/javascript')))->addChild(new HTMLString(vsprintf($format, $args), true));
	}

	/**
	 * TODOC
	 *
	 * @param unknown $action
	 * @return \temple\web\html\HTMLNode
	 */
	public static final function createForm($action, $cssClass = null) {
		return self::createNodeWithClass('form', $cssClass)->setAttributes(['method' => 'post', 'enctype' => 'multipart/form-data', 'action' => $action]) ;
	}

	/**
	 * TODOC
	 *
	 * @param HTMLNode $for
	 * @param string $string
	 * @return \temple\web\html\HTMLNode
	 */
	public static final function createLabelNode(HTMLNode $for, $string) {
		Util::notEmptyString($string) ;
		return self::createNodeWithString('label', $string)->setAttribute('for', $for->getId()) ;
	}

	/**
	 * TODOC
	 *
	 * @param unknown $href
	 * @param unknown $string
	 * @return unknown
	 */
	public static final function createANode($href, $string) {
		return self::createNodeWithString('a', $string)->setAttribute('href', $href) ;
	}

	/**
	 * TODOC
	 *
	 * @param unknown $string
	 * @param string $class
	 * @return \temple\web\html\HTMLNode
	 */
	public static final function createSpanNode($string, $class = NULL) {
		return self::createNodeWithClassAndString('span', $class, $string) ;
	}

	/**
	 * Creates a new table node containing a tbody node.
	 * Every row needs to be added to added to the inner tbody accessible with the key tbody.
	 *
	 * TODOC
	 *
	 * @param string $class
	 */
	public static final function createTableNode($class = NULL) {
		return self::createNodeWithClass('table', $class)->addChild(new HTMLNode('tbody'), 'tbody') ;
	}

	public static final function createCssLinkNode($href, $media = 'all') {
		return new HTMLNode('link', ['rel'=>'stylesheet', 'href'=>$href, 'media'=>$media]) ;
	}
	
}
