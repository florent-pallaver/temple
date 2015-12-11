<?php
namespace temple;

/**
 * Description of _String
 *
 * @author flominou
 */
class _String implements TempleObject {
	
	use Reflection ;
	
	private $s ;

	/**
	 * 
	 * @param string $s
	 */
	public function __construct($s) {
		$this->s = strval($s) ;
	}
	
	public function __toString() {
		return $this->s ;
	}

	public function equals(TempleObject $to = null) {
		return $to instanceof _String && $to->s === $this->s ;
	}

	public function getClass() {
		return self::_class() ;
	}
	
}
