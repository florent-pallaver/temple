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
		$date = \DateTime::createFromFormat(self::SQL_DATE_FORMAT, $notNullValue) ;
		if(!$date) {
			$errors = \DateTime::getLastErrors() ;
			foreach($errors['warnings'] as $w) {
				\temple\Logger::getInstance()->warning($w);
			}
			foreach($errors['errors'] as $e) {
				\temple\Logger::getInstance()->error($e);
			}
		}
		return $date;
	}
	
}