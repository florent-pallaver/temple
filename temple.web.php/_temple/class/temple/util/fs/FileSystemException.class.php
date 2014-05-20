<?php

namespace temple\util\fs;

/**
 * Description of FileSystemException
 *
 * @author florent
 */
class FileSystemException extends \temple\TempleException {

	public function __construct($msg = '', $code = 0, \Exception $cause = null) {
		parent::__construct($msg, $code, $cause);
	}

}
