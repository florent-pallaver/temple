<?php

namespace temple\util\io;

/**
 * Description of ImageFormatException
 *
 * @author florent
 */
class UnsupportedImageFormatException extends ImageProcessingException {

	/**
	 * 
	 * @param \Exception $cause
	 */
	public function __construct(\Exception $cause = null) {
		parent::__construct($cause) ;
	}
	
}
