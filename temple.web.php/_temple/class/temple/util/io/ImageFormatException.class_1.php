<?php

namespace temple\util\io;

/**
 * Description of ImageFormatException
 *
 * @author florent
 */
class UnsupportedImageFormatException extends IOException {

	const FORMAT = "Unsupported image format" ;
	
	public function __construct(\Exception $cause = null) {
		parent::__construct(self::FORMAT, 0, $cause);
	}
	
}
