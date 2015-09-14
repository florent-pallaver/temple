<?php

namespace temple\util\human\sport ;

/**
 * Locales for RugbyUnionPosition
 *
 * @author flominou
 */
final class RugbyUnionPositionLocale {
	
	private function __construct() {}
	
	const HOOKER = 'talonneur' ;
	const PROP = 'pillier' ;
	const LOCK = '2ème ligne' ;
	const FLANKER = '3ème ligne aile' ;
	const NUMBER_8 = '3ème ligne centre' ;
	const SCRUM_HALF = 'demi de mêlée' ;
	const FLY_HALF = 'demi d\'ouverture' ;
	const CENTRE = 'centre' ;
	const WING = 'ailier' ;
	const FULLBACK = 'arrière' ;

	public static $all = [self::HOOKER, self::PROP, self::LOCK, self::FLANKER, self::NUMBER_8, self::SCRUM_HALF, self::FLY_HALF, self::CENTRE, 
		self::WING, self::FULLBACK] ;
	
}
