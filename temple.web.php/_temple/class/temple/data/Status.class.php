<?php

namespace temple\data;

/**
 * Description of Status
 *
 * @author florent
 */
class Status {

	use \temple\Enum ;
	
	/**
	 * @var Status
	 */
	public static $SUCCESS ;
	/**
	 * @var Status
	 */
	public static $INFO ;
	/**
	 * @var Status
	 */
	public static $WARNING ;
	/**
	 * @var Status
	 */
	public static $ERROR ;
	
}
