<?php

namespace temple\util\fs;

/**
 * Description of ResourceCreationException
 *
 * @author florent
 */
class ResourceCreationException extends FileSystemException {

	public function __construct(FileSystemResource $resource, $reason) {
		parent::__construct("unable to create $resource.\n$reason");
	}
	
}
