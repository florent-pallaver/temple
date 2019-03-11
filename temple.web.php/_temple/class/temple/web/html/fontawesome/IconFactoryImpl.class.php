<?php

namespace temple\web\html\fontawesome;

/**
 * Description of IconFactory
 *
 * @author flominou
 */
class IconFactoryImpl extends \temple\web\html\IconFactory {
	
	use \temple\Singleton ;
	
	private static $alertIcons = ['exclamation-circle', 'warning', 'info', 'check'] ;
	
	protected function newIcon($name, array $variations = array()) {
		return new Icon($name, $variations) ;
	}

	public function getAlertIcons() {
		return self::$alertIcons ;
	}

}
