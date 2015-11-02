<?php

namespace temple\web\html\v5 ;

use temple\util\DateUtil ;

/**
 * Description of Time
 *
 * @author flominou
 */
class Time extends \temple\web\html\HTMLNode {
	
	const DEFAULT_FORMAT = \temple\CommonLocale::DATE_FORMAT ;
	
	private static $format = 'Y-m-d\Th:i:s' ;
	
	/**
	 * Constructor
	 */
	public function __construct() {
		parent::__construct('time');
	}
	
	/**
	 * 
	 * @param \DateTime $dt
	 * @return \temple\web\html\bootstrap\Time
	 */
	public function setDatetime(\DateTime $dt) {
		return $this->setAttribute('datetime', $dt->format(self::$format)) ;
	}
	
	/**
	 * 
	 * @param \DateTime $dateTime
	 * @param string $format
	 * @param type $icon
	 * @return \temple\web\html\v5\Time
	 */
	public static final function create(\DateTime $dateTime, $format = self::DEFAULT_FORMAT, $icon = null) {
		$t = new Time() ;
		$t->setDatetime($dateTime) ;
		if($icon) {
			$t->addChild(\temple\web\html\IconFactory::getInstance()->createIcon($icon)) ;
		}
		$t->addChild(new \temple\web\html\HTMLString(DateUtil::localize($dateTime, $format))) ;
		return $t ;
	}
	
}
