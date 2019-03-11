<?php

namespace temple;

/**
 *
 * @author florent
 */
interface TempleObject {
	
	/**
	 * @param TempleObject $to Description
	 * @return boolean
	 */
	function equals(TempleObject $to = null) ;
	
	/**
	 * @return \ReflectionClass
	 */
	function getClass() ;

	/**
	 * @return string
	 */
	function __toString() ;
	
}
