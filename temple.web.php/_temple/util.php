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
function _iod(array $a, $i, $d = null) {
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
	return is_array($v) ? $v : [$v] ;
}
