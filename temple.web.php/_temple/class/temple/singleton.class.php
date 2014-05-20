<?php

namespace temple ;

/**
 * TODOC
 *
 * @author florent
 */
trait Singleton {

	private static $instance ;

	/**
	 * @return object sole instance of this class.
	 */
	public static function getInstance() {
		return self::$instance ;
	}

	private static function _initSingleton() {
		$c = __CLASS__ ;
		self::$instance = new $c() ;
	}

}
