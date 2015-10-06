<?php

namespace temple\controller;

/**
 * Description of AbstractRequestControllerLocale
 *
 * @author florent
 */
abstract class AbstractRequestControllerLocale {

	const INFO_FILES_LIMIT = 'Only %d files can be uploaded at a time, the extra ones have been ignored.';
	const INFO_INVALID_CHARS = 'The field %1$s \'%2$s\' contained invalid characters, they have been replaced.';
	const INFO_MAX_LENGTH = 'The field %1$s exceeded the %2$d characters length limit and has been truncated.';
	
	const WARN_AGE = '\'%1$s\' is not a valid birthdate.\nIf you are not at least %2$d you are breaking the terms and conditions of use of this website.';
	const WARN_INVALID_DATE = '\'%s\' is not a valid date.';
	
	const FAIL_MIN_AGE = 'Minimum allowed age is %s.' ;
	const FAIL_MAX_AGE = 'Maximum allowed age is %s.' ;
	
	const FAIL_INCORRECT_FIELD = 'This field is incorrect.';
	const FAIL_EMPTY_FIELD = 'This field cannot be empty.';

	const FAIL_PASSES = 'Passes do not match' ;
	

}
