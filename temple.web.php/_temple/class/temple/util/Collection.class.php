<?php

namespace temple\util;

/**
 *
 * @author florent
 */
interface Collection extends \Countable, \Iterator {

	function getElementClass() ;
	
	function add(\temple\TempleObject $to) ;
	
	function remove(\temple\TempleObject $to) ;
	
	function contains(\temple\TempleObject $to) ;
	
}
