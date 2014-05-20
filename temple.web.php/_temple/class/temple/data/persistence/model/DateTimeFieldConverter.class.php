<?php

namespace temple\data\persistence\model;

/**
 * Description of DateTimeFieldConverter
 *
 * @author florent
 */
class DateTimeFieldConverter extends AbstractFieldConverter {
	
	use \temple\Singleton ;

	const SQL_DATE_FORMAT = 'Y-m-d H:i:s' ;

	protected function toDBValue0($notNullValue) {
		return '\'' . $notNullValue->format(self::SQL_DATE_FORMAT) . '\'';
	}

	protected function toPHPValue0($notNullValue) {
		return \DateTime::createFromFormat(self::SQL_DATE_FORMAT, $notNullValue) ;
	}
	
}
