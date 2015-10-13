<?php

namespace temple;

/**
 * TODOC
 * <br>
 * If in need to define your own <code>_init()</code> function, make sure to call <code>self::_enumInit()</code> to perform the initialization provided by this trait.
 * @author florent
 */
interface Enumeration {

	/**
	 * TODOC
	 */
	function getOrdinal() ;

	/**
	 * TODOC
	 */
	function getName() ;
	
	function __toString() ;
	
	/**
	 * 
	 * @param type $other
	 * @return boolean
	 */
	function equals($other) ;

}
