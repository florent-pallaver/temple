<?php

namespace temple\util\human ;

/**
 * Locales for Gender
 *
 * @author flominou
 */
final class GenderLocale {
	
	private function __construct() {}
	
	const FEMALE = 'féminin' ;
	const MALE = 'masculin';
	const TRANSEXUAL = 'transexuel' ;
	const OTHER = 'autre' ;
	
	public static $all = [self::FEMALE, self::MALE, self::TRANSEXUAL, self::OTHER] ;
	
}
