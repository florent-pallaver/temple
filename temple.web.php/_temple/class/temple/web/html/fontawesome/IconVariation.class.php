<?php

namespace temple\web\html\fontawesome;

/**
 * Description of IconVariation
 *
 * @author flominou
 */
final class IconVariation implements \temple\web\html\IconVariation {
	
	use \temple\Enum ;
	
	/**
	 * fixes the width of the icon
	 */
	public static $FW ;
	
	/**
	 * Adds border to this icon
	 */
	public static $BORDER ;
	
	/**
	 * 33% increase in size
	 */
	public static $LG ; 
	
	/**
	 * size &ťimes; 2
	 */
	public static $x2 ;
	
	/**
	 * size &ťimes; 3
	 */
	public static $x3 ;
	
	/**
	 * size &ťimes; 4
	 */
	public static $x4 ;
	
	/**
	 * size &ťimes; 5
	 */
	public static $x5 ;
	
	/**
	 * 90° rotation
	 */
	public static $_90 ; 
	
	/**
	 * 180° rotation
	 */
	public static $_180 ;
	
	/**
	 * 270° rotation 
	 */
	public static $_270 ;
	
	/**
	 * Flips the icon horizontally
	 */
	public static $FH ;

	/**
	 * Flips the icon vertically
	 */
	public static $FV ;

	/**
	 * Spins this icon
	 */
	public static $SPIN ;

	/**
	 * Pulses this icon
	 */
	public static $PULSE ;
	
	/**
	 * list item
	 */
	public static $LI ;
	
	private static $css = [
		'fa-fw', 'fa-border', 
		'fa-lg', 'fa-2x', 'fa-3x', 'fa-4x', 'fa-5x', 
		'fa-rotate-90', 'fa-rotate-180', 'fa-rotate-270', 
		'fa-flip-horizontal', 'fa-flip-vertical',
		'fa-spin', 'fa-pulse',
		'fa-li'
		] ;
	
	public function toCssClass() {
		return self::$css[$this->getOrdinal()];
	}
	
	public function __toString() {
		return $this->toCssClass() ;
	}
	
}
