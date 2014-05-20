<?php

namespace temple\data\persistence\db ;

use temple\TempleException;

/**
 * Base exception used when an issue occurs with the database.
 *
 * @author florent
 */
abstract class DBException extends TempleException {

	/**
	 * Constructor.
	 *
	 * @param string $reason - the reason why this exception is thrown
	 * @param number $code - the error code (optionnal, 0 by default)
	 */
	public function __construct($reason, $code = 0) {
		parent::__construct($reason, $code) ;
	}

}
