<?php

namespace temple ;

/**
 * TODOC
 *
 * @author florent
 */
trait LazySingleton {

	private static $_instance = null ;

	/**
	 * @return sole instance of this class.
	 */
	public static function getInstance() {
		if(!self::$_instance) {
			$c = __CLASS__ ;
			self::$_instance = new $c() ;
		}
		return self::$_instance ;
	}

}
