<?php

namespace temple\util\io;

/**
 * Description of ImageProcessingException
 *
 * @author florent
 */
class ImageProcessingException extends IOException {

	/**
	 * 
	 * @param \Exception $cause
	 */
	public function __construct(\Exception $cause = null) {
		parent::__construct('', 0, $cause);
	}
	
}
