<?php

namespace temple\util ;

use temple\IllegalArgumentException;

/**
 * Util class.
 *
 * @author florent
 */
final class Util {

	private function __construct() {}

	/**
	 * Ensures that a variable is not <code>NULL</code>.
	 *
	 * @param mixed $val - the variable to check
	 * @throws IllegalArgumentException - if the variable is <code>NULL</code>.
	 */
	public static function notNull($val) {
		if($val === NULL) {
			throw new IllegalArgumentException('NULL value given') ;
		}
	}

	/**
	 * Ensures that a variable is a not empty or <code>NULL</code> string.
	 *
	 * @param mixed $str - the variable to check
	 * @throws IllegalArgumentException - if the variable is not a string or an empty or <code>NULL</code> string.
	 */
	public static function notEmptyString($str) {
		if(!(is_string($str) && $str)) {
			throw new IllegalArgumentException('Empty string given') ;
		}
	}

	/**
	 * Index or Default
	 *
	 * @param array $array - an array
	 * @param mixed $index - an index
	 * @param mixed $default - a default value, <code>null</code> by default
	 * @return <code>$array[$index]</code> if it is set, $default otherwise
	 */
	public static function iod(array $array, $index, $default = null) {
		return isset($array[$index]) ? $array[$index] : $default ;
	}

	/**
	 * TODOC
	 *
	 * @param string $className
	 */
	public static function getSimpleClassName($className) {
		return substr($className, strrpos($className, '\\') + 1) ;
	}

	/**
	 * TODOC
	 *
	 * @param string $className
	 */
	public static function getNamespace($className) {
		return substr($className, 0, strrpos($className, '\\')) ;
	}

}