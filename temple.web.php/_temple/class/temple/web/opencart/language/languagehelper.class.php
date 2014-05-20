<?php

namespace temple\web\opencart\language ;

/**
 * Helper class to allow access to language entries.
 *
 * @author florent
 */
final class LanguageHelper {

	private static $entries ;

	private function __construct() {}

	/**
	 * TODOC
	 *
	 * @param mixed $entry - the key of the wanted entry
	 * @return the entry corresponding to the given entry key if found, the given entry key otherwise.
	 */
	public static function get($entry) {
		return isset(self::$entries[$entry]) ? self::$entries[$entry] : $entry ;
	}

	/**
	 * TODOC
	 *
	 * @param array $data - the entries to set
	 */
	public static function setEntries(array $data) {
		self::$entries = $data ;
	}

}

?>