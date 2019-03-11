<?php

namespace temple ;

use Exception ;
use \temple\ExceptionHandler ;

/**
 * TODOC
 *
 * @author florent
 */
abstract class TempleException extends Exception {

	/**
	 * Constructor.
	 *
	 * @param string $msg - a message that explains this exception
	 * @param number $code - the error code (0 by default)
	 * @param \Exception $cause - the cause of this exception (<code>null</code> by default)
	 */
	public function __construct($msg = '', $code = 0, Exception $cause = null) {
		parent::__construct($msg, $code, $cause) ;
	}

	/**
	 * Log this exception.
	 */
	public final function log() {
		ExceptionHandler::log($this) ;
	}

}
