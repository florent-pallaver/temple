<?php

namespace temple\data\persistence\db\query ;

/**
 * Base contract for an object to be used as a value.
 *
 * @author florent
 */
interface Value extends QueryPart {

	/**
	 * TODOC
	 *
	 * @param string $comparison
	 * @return string
	 */
	function getOperator($comparison = null) ;

}
