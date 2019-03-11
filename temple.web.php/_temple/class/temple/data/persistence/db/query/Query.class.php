<?php

namespace temple\data\persistence\db\query ;

/**
 * TODOC
 *
 * @author florent
 */
interface Query {

	/**
	 * @return Table the table this query is to be executed on.
	 */
	function getTable() ;

}

