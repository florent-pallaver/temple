<?php

namespace temple\util\io;

/**
 * Description of ResourceCreationException
 *
 * @author florent
 */
class ResourceCreationException extends IOException {

	public function __construct(FileSystemResource $resource, $reason) {
		parent::__construct("unable to create $resource.\n$reason");
	}
	
}
