<?php

namespace temple\data\persistence\db\query;

/**
 * Description of ValueFactory
 *
 * @author florent
 */
abstract class ValueFactory {

	const SQL_DATE_FORMAT = 'Y-m-d H:i:s' ;
	
	private function __construct() {}
	
	/**
	 * 
	 * @param mixed $raw
	 * @return Value
	 * @throws IllegalArgumentException
	 */
	public static function create($raw) {
		if($raw === null) {
			$v = NullValue::getInstance() ;
		} elseif (is_scalar($raw)) {
			$v = new ScalarValue($raw) ;
		} else if (is_array($raw)) {
			$v = new ListValue($raw);
		} else if (is_object($raw)) {
			if($raw instanceof Value) {
				$v = $raw ;
			} elseif ($raw instanceof \DateTime) {
				$v = new ScalarValue($raw->format(self::SQL_DATE_FORMAT)) ;
			}
		}
		if(isset($v)) {
			return $v ;
		}
		throw new IllegalArgumentException('Unable to create a Value object from parameter of type '.gettype($raw));
	}
	
}
