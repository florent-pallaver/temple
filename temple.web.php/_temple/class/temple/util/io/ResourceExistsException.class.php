<?php

namespace temple\util\io;

/**
 * Description of ResourceExistsException
 *
 * @author florent
 */
class ResourceExistsException extends IOException {

	public function __construct(FileSystemResource $resource) {
		parent::__construct("$resource already exists");
	}
	
}
