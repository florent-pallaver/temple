<?php

namespace temple\util ;

/**
 * Date util class.
 *
 * @author florent
 */
final class DateUtil {

	private function __construct() {}

	/**
	 * 
	 * @param \DateTime $bd
	 * @return int
	 */
	public static function age(\DateTime $bd) {
		$now = new \DateTime('now') ;
		$age = $now->format('Y') - $bd->format('Y') ;
		if($now->format('m-d') < $bd->format('m-d')) {
			$age-- ;
		}
		return $age ;
	}
	

}