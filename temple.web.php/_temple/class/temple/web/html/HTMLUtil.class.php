<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace temple\web\html;

/**
 * Description of HTMLUtil
 *
 * @author flominou
 */
final class HTMLUtil {

	private function __construct() {}
	
	/**
	 * Makes an \temple\web\html\HTMLElement out of a given variable.
	 * @param mixed $x some value
	 * @return \temple\web\html\HTMLElement 
	 */
	public static function toHTMLElement($x) {
		if ($x instanceof HTMLElement) {
			$e = $x;
		} elseif ($x instanceof \temple\util\Iconable) {
			$e = IconFactory::getInstance()->createText($x->getIcon(), $x);
		} elseif ($x instanceof \DateTime) {
			// FIXME config format !
			$e = new HS($x->format('l, d F Y, g:i a'));
		} elseif ($x !== null) {
			if (is_array($x)) {
				$a = [];
				foreach ($x as $k => $v) {
					$a[$k] = self::toHTMLElement($v);
				}
				$e = new HTMLElementList($a);
			} else {
				$e = new HTMLString($x);
			}
		} else {
			$e = null;
		}
		return $e;
	}

}
