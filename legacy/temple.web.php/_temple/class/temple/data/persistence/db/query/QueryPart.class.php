<?php

namespace temple\data\persistence\db\query ;

/**
 * Base interface for every object used to build up queries.
 *
 * @author florent
 */
interface QueryPart {

	/**
	 * Exception safe alternative to __toString() magic method ...
	 * @returns string
	 */
	function toString() ;
	
	function __toString() ;

}


