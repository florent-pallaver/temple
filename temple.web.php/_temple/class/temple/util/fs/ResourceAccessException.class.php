<?php

namespace temple\util\fs;

/**
 * Description of ResourceAccessException
 *
 * @author florent
 */
class ResourceAccessException extends FileSystemException {

	public function __construct(FileSystemResource $resource) {
		parent::__construct("access to $resource is not allowed");
	}
	
}
