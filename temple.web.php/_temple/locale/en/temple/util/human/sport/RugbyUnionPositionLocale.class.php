<?php

namespace temple\util\human\sport ;

/**
 * Locales for RugbyUnionPosition
 *
 * @author flominou
 */
final class RugbyUnionPositionLocale {
	
	private function __construct() {}
	
	const HOOKER = 'hooker' ;
	const PROP = 'prop' ;
	const LOCK = 'lock' ;
	const FLANKER = 'flanker' ;
	const NUMBER_8 = 'number 8' ;
	const SCRUM_HALF = 'scrum half' ;
	const FLY_HALF = 'fly half' ;
	const CENTRE = 'centre' ;
	const WING = 'wing' ;
	const FULLBACK = 'fullback' ;

	public static $all = [self::HOOKER, self::PROP, self::LOCK, self::FLANKER, self::NUMBER_8, self::SCRUM_HALF, self::FLY_HALF, self::CENTRE, 
		self::WING, self::FULLBACK] ;
	
}
