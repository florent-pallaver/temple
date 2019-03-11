<?php

namespace temple\util\io;

/**
 * Description of ResourceAccessException
 *
 * @author florent
 */
class ResourceAccessException extends IOException {

	public function __construct(FileSystemResource $resource) {
		parent::__construct("access to $resource is not allowed or this resource does not exists");
	}
	
}
