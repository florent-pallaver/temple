<?php

namespace temple\web\html;

use temple\CommonLocale ;

/**
 * Description of HTMLUtil
 *
 * @author flominou
 */
final class HTMLUtil {

	private function __construct() {}
	
	/**
	 * TODOC
	 * @var type 
	 */
	public static $DATE_FORMAT = CommonLocale::DATE_FORMAT; 
	
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
			$e = new HTMLString(\temple\util\DateUtil::localize($x, self::$DATE_FORMAT));
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
