<?php

namespace temple\data\persistence\model\validation;

/**
 * PregPattern 
 *<br>
 * this constraint is valid only when the value matches the pattern
 * @author florent
 */
class PregPattern implements \temple\data\persistence\model\FieldConstraint {

	private static $msg = 'the given string contains invalid characters' ;
	
	private $pattern;
	
	private $delimiter ;
	
	private $match ;
	
	/**
	 * Constructor.
	 * 
	 * @param string $pattern a preg pattern (ie with its delimiter around)
	 * @param string $delimiter default to '/'
	 */
	public function __construct($pattern, $delimiter = '/', $match = true) {
		$this->pattern = $pattern;
		$this->delimiter = $delimiter{0} ;
		$this->match = $match ;
	}

	public function validate($value) {
		if($value) {
			$m = preg_match($this->delimiter . $this->pattern . $this->delimiter, $value) ;
			if(($this->m || $m) && !($this->m && $m)) {
				throw new ValidationException(self::$msg) ;
			}
		}
	}
	
}
