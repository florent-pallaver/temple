<?php

namespace web ;

use \AppException ;

class HTTPGetException extends AppException {

	public function __construct($httpStatusCode, $msg = null) {
		parent::__construct($msg == null ? $httpStatusCode : $msg, $httpStatusCode) ;
	}

}

?>