<?php

namespace temple\data\persistence\db\query ;

/**
 * TODOC
 *
 * @author florent
 */
interface Condition extends Comparison {

	/**
	 * TODOC
	 *
	 */
	function isEmpty() ;

	/**
	 * Adds a Comparison to this Condition.
	 *
	 * @param Comparison $comp - the comparison to add.
	 * @return Condition this object in order to be able to chain method calls on this object.
	 */
	function addComparison(Comparison $comp) ;

}
