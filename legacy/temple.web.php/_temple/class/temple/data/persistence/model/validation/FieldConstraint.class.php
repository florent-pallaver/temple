<?php

namespace temple\data\persistence\model;

/**
 * Description of FieldConstraint
 *
 * @author florent
 */
interface FieldConstraint {

	/**
	 * @throws ValidationException
	 */
	function validate($value) ;
	
}
