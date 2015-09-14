<?php

namespace temple\util\human ;

/**
 * Locales for Gender
 *
 * @author flominou
 */
final class GenderLocale {
	
	private function __construct() {}
	
	const FEMALE = 'female' ;
	const MALE = 'male';
	const TRANSEXUAL = 'transexual' ;
	const OTHER = 'other' ;
	
	public static $all = [self::FEMALE, self::MALE, self::TRANSEXUAL, self::OTHER] ;
	
}
