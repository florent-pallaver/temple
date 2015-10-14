<?php

namespace temple\data\persistence\db\query ;

/**
 * TODOC
 *
 * @author florent
 */
final class JoinType extends \temple\Enumeration {

	/**
	 * @var JoinType
	 */
	public static $CROSS_JOIN ;

	/**
	 * @var JoinType
	 */
	public static $INNER_JOIN ;

	/**
	 * @var JoinType
	 */
	public static $LEFT_OUTER_JOIN ;

	/**
	 * @var JoinType
	 */
	public static $RIGHT_OUTER_JOIN ;

	private static $formats = array(' CROSS JOIN %s', ' INNER JOIN %s ON %s', ' LEFT OUTER JOIN %s ON %s', ' RIGHT OUTER JOIN %s ON %s') ;

	/**
	 * TODOC
	 */
	public function getFormat() {
		return self::$formats[$this->ordinal] ;
	}

}

