<?php

namespace temple\data\persistence\db ;

/**
 * Exception used when a connection to a database issue occurs.
 *
 * @author florent
 */
class ConnectionException extends DBException {

	/**
	 * Constructor.
	 *
	 * @param string $reason - the reason why this exception is thrown
	 * @param number $code - the error code (optionnal, 0 by default)
	 */
	public function __construct($reason, $code = 0) {
		parent::__construct(sprintf('Connection failed: %s', $reason), $code) ;
	}

}
