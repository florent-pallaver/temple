<?php

namespace temple\data\persistence\model\validation;

/**
 * Description of Range 
 *
 * @author florent
 */
class Range implements \temple\data\persistence\model\FieldConstraint {

	private static $msg = 'the number must be between %s and %s' ;
	
	private $min ;
	
	private $max ;
	
	public function __construct($max = null, $min = null) {
		$this->min = $min;
		$this->max = $max;
	}

	public function validate($value) {
		if($value !== null) {
			if(($this->min !== null && $value < $this->min) || ($this->max !== null && $value > $this->max)) {
				throw new ValidationException(sprintf(self::$msg, $this->min, $this->max)) ;
			}
		}
	}
	
}
