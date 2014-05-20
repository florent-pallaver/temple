<?php

namespace temple\web\opencart ;

use temple\TempleException;

/**
 * Base class for Opencart exceptions.
 *
 * @author florent
 */
class OpencartException extends TempleException {

	/**
	 * Constructor.
	 *
	 * @see TempleException::__construct
	 */
	public function __construct($msg = '', $code = 0, \Exception $cause = null) {
		parent::__construct($msg, $code, $cause) ;
	}

}
