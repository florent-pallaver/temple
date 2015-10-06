<?php

namespace temple\web\html\fontawesome;

/**
 * TODOC
 *
 * @author flominou
 */
final class IconRotate {
	
	use \temple\Enum ;
	
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
	
	private static $strings = ['fa-rotate-90', 'fa-rotate-180', 'fa-rotate-270'] ;
	
	public function __toString() {
		return self::$strings[$this->getOrdinal()];
	}
	
}
