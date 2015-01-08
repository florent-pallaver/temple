<?php

namespace temple\data\persistence\model\validation;

/**
 * Description of ValidationException
 *
 * @author florent
 */
class ValidationException extends \temple\TempleException {

	public function __construct($msg) {
		parent::__construct($msg);
	}
	
}
