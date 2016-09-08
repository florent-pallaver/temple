<?php

namespace temple\util\io;

class ImageProcessingException extends IOException {

	/**
	 * 
	 * @param \Exception $cause
	 */
	public function __construct(\Exception $cause = null) {
		parent::__construct('', 0, $cause);
	}
	
}
