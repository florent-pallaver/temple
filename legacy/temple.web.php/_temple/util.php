<?php

/**
 * Index Or Default
 * 
 * @param array $a an array
 * @param mixed $i an index
 * @param mixed $d a default value, null by default
 * @return mixed the value at the given index in the given array if it exists, 
 *  the given default value otherwise.
 */
function _iod(array &$a, $i, $d = null) {
    return isset($a[$i]) ? $a[$i] : $d ;
}

/**
 * Default If False
 * @param mixed $v
 * @param mixed $d a default value
 * @return mixed $d if $v evaluates to false, $v otherwise.
 */
function _dif($v, $d) {
    return $v ? $v : $d ;
}

/**
 * Ensure Is Array
 * @param mixed $v
 * @return array
 */
function _eia($v) {
	return $v === null ? [null] : (is_array($v) ? $v : [$v]) ;
}

/**
 * Returns a string version of a param
 * @param mixed $v
 * @return string
 */
function _str($v) {
	return is_array($v) ? print_r($v, true) : ($v instanceof DateTime ? temple\util\DateUtil::localize($v): strval($v)) ;
}

/**
 * First Char To Lower Case
 * @param string $s a string
 * @return string the given string with its first char tin lower case
 */
function _fctlc($s) {
    return strtolower($s{0}) . substr($s, 1) ;
}

/**
 * First Char To Upper Case
 * @param string $s a string
 * @return string the given string with its first char tin lower case
 */
function _fctuc($s) {
    return strtoupper($s{0}) . substr($s, 1) ;
}

/**
 * Enumeration To Int
 * @param temple\Enumeration | mixed $e
 * @return int
 */
function _eti($e) {
	return $e instanceof temple\Enumeration ? $e->ordinal() : ($e === null ? null : intval($e)) ;
}

/**
 * Value To Scalar
 * @param mixed $v
 * @return scalar ordinal if Enumeration, null if null and strval otherwise
 */
function _vts($v) {
	return $v instanceof temple\Enumeration 
			? $v->ordinal() 
			: ($v === null 
					? $v 
					: strval($v)) ;
}
