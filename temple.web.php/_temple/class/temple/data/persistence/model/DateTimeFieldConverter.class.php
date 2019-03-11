<?php

namespace temple\data\persistence\model;

use temple\util\TempleDateTime ;

/**
 * Description of DateTimeFieldConverter
 *
 * @author florent
 */
class DateTimeFieldConverter extends AbstractDateFieldConverter {
	
	use \temple\Singleton ;

	protected function __construct() {
		parent::__construct(TempleDateTime::SQL_DATE_TIME_FORMAT);
	}
	
}
