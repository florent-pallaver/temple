<?php

namespace temple\web\html\bootstrap;

use temple\data\Status ;
use temple\data\Messages ;

/**
 * Util component to display messages<br>
 * Add this component to your bootstrap html5 document in order to add an alert for every messages in your session.
 *
 * @author florent
 */
class Alerts extends AbstractComponent {
	
	const ID = '_templeAlerts' ;
	
	private static $variant = ['danger', 'warning', 'info', 'success'] ;
	
	public function __construct() {
		parent::__construct('div');
		$this->setAttribute('id', self::ID) ;
		$messages = Messages::getInstance()->popAll() ;
		foreach (Status::getAll() as $s) {
			$k = $s->getOrdinal() ;
			if (isset($messages[$k]) && $messages[$k]) {
				foreach ($messages[$k] as $msg) {
					$this->addAlert($msg, $s->getOrdinal());
				}
			}
		}
	}
	
	private function addAlert($msg, $k) {
		$alert = ComponentFactory::createComponent('div', 'fade in alert alert-dismissable alert-'.self::$variant[$k])
				->setAttribute('tabindex', '-1') 
				->addChild(Button::create(new \temple\web\html\HTMLString('&times;', true))
						->addCssClass('close')
						->setAttributes(['data-dismiss'=>'alert', 'aria-hidden'=>'true']))
				->addChild($this->createIconedText($this->getAlertIcon($k), $msg)) ;
		$this->addChild($alert) ;
	}
}
