<?php

namespace temple\web\opencart ;

use temple\Logger;

final class Helper {

	private static $registry = null ;

	private function __construct() {}

	/**
	 *
	 * TODOC
	 *
	 * @return unknown
	 * @throws OpencartException if the registry is not set yet.
	 */
	public static function getRegistry() {
		if(self::$registry === null) {
			throw new OpencartException('Registry not set!') ;
		}
		return self::$registry ;
	}

	/**
	 *
	 * TODOC
	 *
	 * @param unknown $registry
	 */
	public static function setRegistry($registry) {
		if(self::$registry === null) {
			Logger::getInstance()->finest('setting the registry') ;
			self::$registry = $registry ;
		}
	}

}
