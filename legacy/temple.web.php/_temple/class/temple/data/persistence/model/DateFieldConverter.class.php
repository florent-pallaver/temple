<?php

namespace temple\data\persistence\model;

use temple\util\TempleDateTime ;

/**
 * Description of DateFieldConverter
 *
 * @author florent
 */
class DateFieldConverter extends AbstractDateFieldConverter {
	
	use \temple\Singleton ;

	protected function __construct() {
		parent::__construct(TempleDateTime::SQL_DATE_FORMAT);
	}
	
}
