<?php

namespace temple\util\human\sport ;

/**
 * Description of RugbyUnionPosition
 *
 * @author florent
 */
final class RugbyUnionPosition extends \temple\Enumeration {

	/**
	 * @var RugbyUnionPosition
	 */
	public static $HOOKER;

	/**
	 * @var RugbyUnionPosition
	 */
	public static $PROP;

	/**
	 * @var RugbyUnionPosition
	 */
	public static $LOCK;

	/**
	 * @var RugbyUnionPosition
	 */
	public static $FLANKER;

	/**
	 * @var RugbyUnionPosition
	 */
	public static $NUMBER_8;

	/**
	 * @var RugbyUnionPosition
	 */
	public static $SCRUM_HALF;

	/**
	 * @var RugbyUnionPosition
	 */
	public static $FLY_HALF;

	/**
	 * @var RugbyUnionPosition
	 */
	public static $CENTRE;

	/**
	 * @var RugbyUnionPosition
	 */
	public static $WING;

	/**
	 * @var RugbyUnionPosition
	 */
	public static $FULLBACK;

	private static $numbers = [[2], [1, 3], [4, 5], [6, 7], [8], [9], [10], [12, 13], [11, 14], [15]] ;
	
	/**
	 * TODOC
	 * @return array
	 */
	public function getNumbers() {
		return self::$numbers[$this->ordinal()] ;
	}
	
	/**
	 * TODOC
	 * @return boolean
	 */
	public function isForward() {
		return $this->ordinal() < self::$SCRUM_HALF->ordinal() ; 
	}
	
	/**
	 * TODOC
	 * @return boolean
	 */
	public function isBack() {
		return $this->ordinal() > self::$NUMBER_8->ordinal() ; 
	}
	
	public function __toString() {
		return RugbyUnionPositionLocale::$all[$this->ordinal()];
	}

}
