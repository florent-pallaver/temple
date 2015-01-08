<?php

namespace temple\data\persistence\model\validation;

/**
 * Description of NotNull
 *
 * @author florent
 */
final class NotNull implements \temple\data\persistence\model\FieldConstraint {

	use \temple\Singleton ;
	
	private function __construct() {}

	public function validate($value) {
		if($value === null) {
			throw new ValidationException('the given value is null') ;
		}
	}
	
}
