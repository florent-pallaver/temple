<?php

namespace temple\util\human\sport ;

/**
 * Description of RugbyUnionPosition
 *
 * @author florent
 */
final class RugbyUnionPosition {

	use \temple\Enum;

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
		return self::$numbers[$this->getOrdinal()] ;
	}
	
	/**
	 * TODOC
	 * @return boolean
	 */
	public function isForward() {
		return $this->getOrdinal() < self::$SCRUM_HALF->getOrdinal() ; 
	}
	
	/**
	 * TODOC
	 * @return boolean
	 */
	public function isBack() {
		return $this->getOrdinal() > self::$NUMBER_8->getOrdinal() ; 
	}
	
	public function __toString() {
		return RugbyUnionPositionLocale::$all[$this->getOrdinal()];
	}

}
