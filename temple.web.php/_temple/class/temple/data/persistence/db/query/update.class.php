<?php

namespace temple\data\persistence\db\query ;

/**
 * TODOC
 *
 * @author florent
 */
interface Update extends ConditionnedQuery {

	/**
	 *
	 * TODOC
	 * @return array
	 */
	function getAssignments() ;

	/**
	 *
	 * TODOC
	 *
	 * @param Field $field
	 * @param Value $value
	 * @return Update
	 */
	function addAssignment(Field $field, Value $value) ;

}
