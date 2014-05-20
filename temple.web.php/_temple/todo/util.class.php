<?php

use \persistence\Relation ;
use \persistence\OneToOne ;
use \persistence\ManyToOne ;

/**
 *
 * @author florent
 * @deprecated
 */
final class Util {

	const DOMAIN_PATTERN = '/:\/\/(.*\\.[a-z][a-z][a-z]?[a-z]?)\//' ;

	private function __construct() {}

	public static function formatDateTime($dateTime, $outputFormat = 'M d Y, h:i A', $inputFormat = 'Y-m-d H:i:s') {
		$time = DateTime::createFromFormat($inputFormat, $dateTime) ;
		return $time->format($outputFormat) ;
	}

	public static function extractDomain($url) {
		preg_match(self::DOMAIN_PATTERN, $url, $matches) ;
		if(count($matches) < 2) {
			logThat('Unable to find domain for URL: ' . $url) ;
			$d = '' ;
		} else {
			$d = $matches[1] ;
		}
		return $d ;
	}

}

?>