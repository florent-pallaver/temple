<?php

namespace temple\util\fs;

/**
 * Description of ResourceExistsException
 *
 * @author florent
 */
class ResourceExistsException extends FileSystemException {

	public function __construct(FileSystemResource $resource) {
		parent::__construct("$resource already exists");
	}
	
}
