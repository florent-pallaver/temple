<?php

namespace temple\util;

/**
 * Util class extending \DateTime in order to provide the __toString() implementation and some other date time util methods.
 *
 * @author flominou
 */
class TempleDateTime extends \DateTime {

	const SQL_DATE_FORMAT = 'Y-m-d';

	const SQL_DATE_TIME_FORMAT = 'Y-m-d H:i:s';

	/**
	 * Return difference between $this and $now
	 *
	 * @param Datetime|String $now
	 * @return DateInterval
	 * @author gmblar gmblarphp@gmail.com
	 */
//	public function diff($now = 'NOW') {
//		if (!($now instanceOf DateTime)) {
//			$now = new DateTime($now);
//		}
//		return parent::diff($now);
//	}

	/**
	 * Return Age in Years
	 *
	 * @param Datetime|String $now
	 * @return Integer
	 * @author gmblar gmblarphp@gmail.com
	 */
//	public function getAge($now = 'NOW') {
//		return $this->diff($now)->format('%y');
//	}

	// FIXME rajouter le parameter $timezone
	public static function createFromFormat($format, $time, $timezone = null) {
		$dt = parent::createFromFormat($format, $time);
		$tdt = new TempleDateTime();
		$tdt->setTimestamp($dt->getTimestamp());
		$tdt->setTimezone($dt->getTimezone());
		return $tdt;
	}

	public function __toString() {
		return $this->format(self::SQL_DATE_TIME_FORMAT);
	}

}
