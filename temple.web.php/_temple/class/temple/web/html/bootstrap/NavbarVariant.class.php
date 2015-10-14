<?php

namespace temple\web\html\bootstrap;

/**
 * Description of NavbarVariant
 *
 * @author florent
 */
class NavbarVariant extends \temple\Enumeration {

	/**
	 * @var NavbarVariant
	 */
	public static $_;

	/**
	 * @var NavbarVariant
	 */
	public static $FIXED__TOP;

	/**
	 * @var NavbarVariant
	 */
	public static $FIXED__BOTTOM;

	/**
	 * @var NavbarVariant
	 */
	public static $INVERSE;

	/**
	 * @var NavbarVariant
	 */
	public static $BTN;

	/**
	 * @var NavbarVariant
	 */
	public static $LEFT;

	/**
	 * @var NavbarVariant
	 */
	public static $RIGHT;

	/**
	 * @var NavbarVariant
	 */
	public static $FORM;
	
	private $string = null;

	public function __toString() {
		if ($this->string === null) {
			$this->string = 'navbar-' . strtolower(str_replace('__', '-', $this->getName()));
		}
		return $this->string;
	}

	private static function _init() {
		self::$_->string = '';
	}

}
