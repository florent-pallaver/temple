<?php

namespace temple\web\html\bootstrap;

use temple\web\html\HTMLNodeFactory as NF;
use temple\web\html\HTMLString as HS;

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
	public static $CARET;

	/**
	 * @var \temple\web\html\HTMLNode
	 */
	public static $CARET_BUTTON;

	/**
	 * @var \temple\web\html\HTMLNode
	 */
	public static $BR;
	
	/**
	 * @var \temple\web\html\HTMLNode
	 */
	public static $CLEARFIX ;

	/**
	 * Makes an \temple\web\html\HTMLElement out of a given variable.
	 * @param mixed $x some value
	 * @return \temple\web\html\HTMLElement 
	 */
	public static function toHTMLElement($x) {
		if ($x instanceof \temple\web\html\HTMLElement) {
			$e = $x;
		} elseif($x instanceof \DateTime) {
			$e = new HS($x->format('l, d F Y, g:i a')) ;
		} elseif ($x !== null) {
			if(is_array($x)) {
				$a = [] ;
				foreach ($x as $k=>$v) {
					$a[$k] = self::toHTMLElement($v) ;
				}
				$e = new \temple\web\html\HTMLElementList($a) ;
			} else {
				$e = new HS($x);
			}
		} else {
			$e = null;
		}
		return $e;
	}

	/**
	 * 
	 * @param string $nodeName
	 * @param string $cssClass
	 * @param mixed $content
	 * @return \temple\web\html\HTMLNode
	 */
	public static function createComponent($nodeName, $cssClass = null, $content = null) {
		return NF::createNodeWithClass($nodeName, $cssClass)->addChild(self::toHTMLElement($content));
	}

	/**
	 * 
	 * @param string $text
	 * @param type $cssClass
	 * @param type $formatted
	 * @return \temple\web\html\HTMLNode
	 */
	public static function createP($text, $cssClass = null, $formatted = false) {
		return self::createComponent('p', $cssClass)
						->addChild(new HS($text, $formatted));
	}

	/**
	 * 
	 * @param string $cssClass
	 * @return \temple\web\html\HTMLNode
	 */
	public static function createDiv($cssClass = null, $content = null) {
		return self::createComponent('div', $cssClass, $content);
	}

	/**
	 * @return \temple\web\html\HTMLNode
	 */
	public static function createClearfix() {
		return self::createDiv('clearfix');
	}

	/**
	 * 
	 * @param type $label
	 * @param type $cssClass
	 * @return \temple\web\html\HTMLNode
	 */
	public static function createLabel($label, $cssClass) {
		return NF::createNodeWithClassAndString('label', $cssClass, $label);
	}

	/**
	 * 
	 * @param type $text
	 * @param type $cssClass
	 * @return \temple\web\html\HTMLNode
	 */
	public static function createBadge($text, $cssClass = null) {
		return NF::createNodeWithClassAndString('span', 'badge', $text)->addCssClass($cssClass);
	}

	/**
	 * 
	 * @return \temple\web\html\HTMLNode
	 */
	public static function createFieldset($disabled = true, $cssClass = null, $jquery = true) {
		$f = self::createComponent('fieldset', $cssClass)
						->setAttribute('disabled', $disabled ? 'disabled' : null);
		if($jquery) {
			$f->addCssClass(Component::JQUERYFIELD_CSS_CLASS) ;
		}
		return $f ;
	}

	private static function _init() {
		self::$CARET = NF::createNodeWithClass('span', 'caret');
		self::$CARET_BUTTON = NF::createNode('button', ['class' => 'btn btn-primary dropdown-toggle'], ['toggle' => 'dropdown'])->addChild(self::$CARET);
		self::$BR = NF::createNode('br');
		self::$CLEARFIX = NF::createNodeWithClass('div', 'clearfix') ;
	}

}
