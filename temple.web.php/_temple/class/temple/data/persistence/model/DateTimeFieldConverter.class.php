<?php

namespace temple\data\persistence\model;

use temple\util\TempleDateTime ;

/**
 * Description of DateTimeFieldConverter
 *
 * @author florent
 */
class DateTimeFieldConverter extends AbstractFieldConverter {
	
	use \temple\Singleton ;

	protected function toDBValue0($notNullValue) {
		return '\'' . $notNullValue->format(TempleDateTime::SQL_DATE_FORMAT) . '\'';
	}

	protected function toPHPValue0($notNullValue) {
		$date = TempleDateTime::createFromFormat(TempleDateTime::SQL_DATE_FORMAT, $notNullValue) ;
		if(!$date) {
			$errors = TempleDateTime::getLastErrors() ;
			foreach($errors['warnings'] as $w) {
				\temple\Logger::getInstance()->warning($w);
			}
			foreach($errors['errors'] as $e) {
				\temple\Logger::getInstance()->severe($e);
			}
		}
		return $date;
	}
	
}
