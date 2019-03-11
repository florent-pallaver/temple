<?php

namespace temple\util;

/**
 *
 * @author florent
 */
interface Collection extends \Countable, \Iterator {

	/**
	 * @return \ReflectionClass
	 */
	function getElementClass() ;
	
	/**
	 * 
	 * @param \temple\TempleObject $to
	 * @return boolean true if the object has been added, false otherwise
	 */
	function add(\temple\TempleObject $to) ;
	
	function remove(\temple\TempleObject $to) ;
	
	function contains(\temple\TempleObject $to) ;
	
}
