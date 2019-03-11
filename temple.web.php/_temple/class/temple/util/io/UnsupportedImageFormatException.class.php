<?php

namespace temple\util\io;

class UnsupportedImageFormatException extends ImageProcessingException {

    /**
     * 
     * @param \Exception $cause
     */
    public function __construct(\Exception $cause = null) {
        parent::__construct($cause);
    }

}
