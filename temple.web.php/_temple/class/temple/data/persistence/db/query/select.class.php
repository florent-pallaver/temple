<?php

namespace temple\data\persistence\db\query ;

/**
 * TODOC
 *
 * @author florent
 */
interface Select extends ConditionnedQuery {

	/**
	 * TODOC
	 * @var string - the constant to use when wanting to select all fields.
	 */
	const ALL_FIELDS = '*' ;

	/**
	 * @return array TODOC
	 */
	function getFields() ;

	/**
	 * Adds a field to select with this query.
	 *
	 * @param Field $field - the field to get
	 * @return Select this object in order to be able to chain method calls on this object.
	 */
	function addField(Field $field) ;

}