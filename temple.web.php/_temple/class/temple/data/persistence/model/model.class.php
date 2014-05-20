<?php

namespace temple\data\persistence\model ;

/**
 *
 * TODOC
 *
 * @author florent
 */
interface Model extends \JsonSerializable {

	const STRING_FORMATTER = '\data\model\simpleStringFormatter' ;

	const VALUE_OR_NULL_FORMATTER = '\data\model\valueOrNullFormatter' ;

	/**
	 * @return \ReflectionClass this object class.
	 */
	function getClass() ;

	/**
	 * @return mixed TODOC
	 */
	function getId() ;

}

function simpleStringFormatter($string) {
	return $string === null ? 'NULL' : '\'' . $string . '\'' ;
}

function valueOrNullFormatter($value) {
	return $value === null ? 'NULL' : $value ;
}
