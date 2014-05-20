<?php

namespace temple\data\persistence\model;

/**
 * Description of FieldConstraint
 *
 * @author florent
 */
interface FieldConstraint {

	function validate($value) ;
	
}
