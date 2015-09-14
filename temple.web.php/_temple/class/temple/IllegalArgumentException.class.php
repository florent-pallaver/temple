<?php

namespace temple ;

/**
 * Exception to be thrown whenever an illegal argument is passed-in.
 *
 * @author florent
 */
final class IllegalArgumentException extends TempleException {

	/**
	 * Constructor.
	 *
	 * @param string $msg - a message explaining why this exception is thrown.
	 */
	public function __construct($msg) {
		parent::__construct($msg) ;
	}

}
