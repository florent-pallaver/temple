<?php

namespace temple\util ;

/**
 * Date util class.
 *
 * @author florent
 */
final class DateUtil {

	/**
	 * @var string day's full string format
	 */
	const LONG_DAY_FORMAT = '%A' ;
	
	/**
	 * @var string day's short string format
	 */
	const SHORT_DAY_FORMAT = '%a' ;
	
	/**
	 * @var string month's full string format
	 */
	const LONG_MONTH_FORMAT = '%B' ;
	
	/**
	 * @var string month's short string format
	 */
	const SHORT_MONTH_FORMAT = '%b' ;
	
	private function __construct() {}

	/**
	 * 
	 * @param \DateTime $bd
	 * @return int
	 */
	public static function age(\DateTime $bd) {
		$now = self::now() ;
		$age = $now->format('Y') - $bd->format('Y') ;
		if($now->format('m-d') < $bd->format('m-d')) {
			$age-- ;
		}
		return $age ;
	}
	
	/**
	 * 
	 * @param \DateTime $d
	 * @param string $format
	 * @return string
	 */
	public static function localize(\DateTime $d, $format = \temple\CommonLocale::LONG_DATE_FORMAT) {
		return strftime($format, $d->getTimestamp()) ;
	}

	/**
	 * @return \DateTime
	 */
	public static function now() {
		return new \DateTime('now') ;
	}
	
}