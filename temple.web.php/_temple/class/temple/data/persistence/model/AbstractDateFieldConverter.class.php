<?php

namespace temple\data\persistence\model;

/**
 * TODOC
 * 
 * @author florent
 */
abstract class AbstractDateFieldConverter extends AbstractFieldConverter {
	
	private $format ;

	protected function __construct($format) {
		parent::__construct() ;
		$this->format = $format;
	}
	
	protected function toDBValue0($notNullValue) {
		return '\'' . $notNullValue->format($this->format) . '\'';
	}

	protected function toPHPValue0($notNullValue) {
		$date = \DateTime::createFromFormat($this->format, $notNullValue) ;
		if(!$date) {
			$errors = \DateTime::getLastErrors() ;
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
