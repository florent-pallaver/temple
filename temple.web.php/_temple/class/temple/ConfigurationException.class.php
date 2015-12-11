<?php

namespace temple ;

/**
 * Exception to be thrown whenever a configuration error occurs.
 *
 * @author florent
 */
final class ConfigurationException extends TempleException {

	/**
	 * Constructor.
	 *
	 * @param string $msg - a message explaining why this exception is thrown.
	 * @param Exception $cause - the cause of this exception (<code>null</code> by default).
	 */
	public function __construct($msg, \Exception $cause = null) {
		parent::__construct($msg, 0, $cause) ;
	}

}
