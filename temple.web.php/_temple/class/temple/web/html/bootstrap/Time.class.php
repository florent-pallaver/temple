<?php

namespace temple\web\html\bootstrap;

/**
 * Description of Time
 *
 * @author flominou
 */
class Time extends AbstractComponent {
	
	public function __construct($cssClass = null) {
		parent::__construct('time', $cssClass);
	}
	
	public static final function create(\DateTime $dateTime) {
		
	}
	
}
