<?php

namespace temple\web\html\bootstrap;

/**
 * Description of IconFactory
 *
 * @author flominou
 */
class IconFactoryImpl extends \temple\web\html\IconFactory {
	
	use \temple\Singleton ;
	
	private static $alertIcons = ['exclamation-sign', 'warning-sign', 'info-sign', 'ok-sign'] ;
	
	protected function newIcon($name, array $variations = array()) {
		return new Icon($name) ;
	}
	
	public function getAlertIcons() {
		return self::$alertIcons ;
	}

	
}
