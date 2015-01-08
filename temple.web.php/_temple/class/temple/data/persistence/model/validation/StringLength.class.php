<?php

namespace temple\data\persistence\model\validation;

/**
 * Description of StringLength 
 *
 * @author florent
 */
class StringLength implements \temple\data\persistence\model\FieldConstraint {

	private static $msg = 'the given string is %s characters long and should be between %s and %s long' ;
	
	private $min ;
	
	private $max ;
	
	public function __construct($max = -1, $min = 0) {
		$this->min = $min;
		$this->max = $max;
	}

	public function validate($value) {
		if($value) {
			$l = strlen($value) ;
			if($l < $this->min || ($this->max > 0 && $l > $this->max)) {
				throw new ValidationException(sprintf(self::$msg, $l, $this->min, $this->max)) ;
			}
		}
	}
	
}
