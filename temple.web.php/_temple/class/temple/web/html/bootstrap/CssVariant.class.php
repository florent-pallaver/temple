<?php

namespace temple\web\html\bootstrap;

/**
 * Description of CssVariant
 *
 * @author florent
 */
final class CssVariant {

	use \temple\Enum ;
	
	/**
	 * Special CssVariant to use when none is needed/wanted.
	 * @var CssVariant
	 */
	public static $_ ;
	/**
	 * @var CssVariant
	 */
	public static $DEFAULT ;
	/**
	 * @var CssVariant
	 */
	public static $PRIMARY ;
	/**
	 * @var CssVariant
	 */
	public static $SUCCESS ;
	/**
	 * @var CssVariant
	 */
	public static $INFO ;
	/**
	 * @var CssVariant
	 */
	public static $WARNING ;
	/**
	 * @var CssVariant
	 */
	public static $DANGER ;
	/**
	 * @var CssVariant
	 */
	public static $XS ;
	/**
	 * @var CssVariant
	 */
	public static $SM ;
	/**
	 * @var CssVariant
	 */
	public static $LG ;
	/**
	 * @var CssVariant
	 */
	public static $DEFAULT_XS ;
	/**
	 * @var CssVariant
	 */
	public static $DEFAULT_SM ;
	/**
	 * @var CssVariant
	 */
	public static $DEFAULT_LG ;
	/**
	 * @var CssVariant
	 */
	public static $PRIMARY_XS ;
	/**
	 * @var CssVariant
	 */
	public static $PRIMARY_SM ;
	/**
	 * @var CssVariant
	 */
	public static $PRIMARY_LG ;
	/**
	 * @var CssVariant
	 */
	public static $SUCCESS_XS ;
	/**
	 * @var CssVariant
	 */
	public static $SUCCESS_SM ;
	/**
	 * @var CssVariant
	 */
	public static $SUCCESS_LG ;

	private $components = null ;
	
	/**
	 * @param string $cssBaseClass
	 * @return string
	 */
	public function compose($cssBaseClass) {
		if($this->components === null) {
			$this->components = explode('_', strtolower($this->getName())) ;
		}
		$cssClasses = $cssBaseClass ;
		foreach($this->components as $c) {
			$cssClasses .= ' ' . $cssBaseClass . '-' . $c ;
		}
		return $cssClasses ;
	}
	
	private static function _init() {
		self::$_->components = [] ;
	}
	
}
