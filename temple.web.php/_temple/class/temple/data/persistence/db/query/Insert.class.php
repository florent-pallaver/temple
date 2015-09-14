<?php

namespace temple\data\persistence\db\query ;

/**
 * TODOC
 *
 * @author florent
 */
interface Insert extends Query {

	/**
	 *
	 * TODOC
	 */
	function getFields() ;

	/**
	 * TODOC
	 *
	 * @param array $fields
	 * @return Insert
	 */
	function setFields(array $fields = null) ;

	/**
	 * TODOC
	 */
	function getTuples() ;

	/**
	 * TODOC
	 *
	 * @param array $tuple
	 * @return Insert
	 */
	function addTuple(array $tuple) ;

}