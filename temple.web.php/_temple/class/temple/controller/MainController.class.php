<?php

namespace temple\controller;

use temple\URL;
use temple\Logger;
use temple\ClassLoader ;

/**
 * Description of MainController
 *
 * @author florent
 */
final class MainController extends AbstractRequestController {

	use \temple\Singleton;

	private static $suffix = Config::CONTROLLER_CLASS_SUFFIX ;
	private static $defaultView = 'home';
	private static $viewNames = [];

	private static $locales = ['en' => 'en_GB.utf8', 'fr' => 'fr_FR.utf8'] ;
	
	public function getActionDescription() {
		return 'process request';
	}

	public function createResponse() {
		$r = $this->getResponse();
		\temple\data\Session::getInstance()->end();
		if (!($r instanceof \temple\view\Renderable)) {
			Logger::getInstance()->fatal('The response returned was not an instance of \\temple\\view\\Renderable.');
			$this->failure();
		}
		return $r;
	}

	/**
	 * 
	 * @return \temple\view\Renderable
	 * @throws ActionException
	 */
	private function getResponse() {
		$vn = self::$defaultView;
		$a = $this->getStringGetParam(URL::ACTION_PARAMETER, self::$defaultEmpty) ;
		$viewName = $this->getStringGetParam(URL::VIEW_PARAMETER, self::$defaultEmpty);
		if ($viewName) {
			// the view name does not matter when we're doing an action (ie $a == true)
			if ($a || array_search($viewName, self::$viewNames) !== false) {
				$vn = &$viewName;
			}
		}
		if($this->logger->isDebugLoggable()) {
			$this->logger->debug("request view = '$viewName' used view = '$vn' action = '$a'") ;
		}
		$cn = Config::$CONTROLLERS_BASE_NAMESPACE . '\\' . ($a && $a != Config::$VIEW_ACTION ? ($vn . '\\'. _fctuc($a)) : _fctuc($vn)) . self::$suffix;
		// specify locale before creating response and causing any exception
		// FIXME un peu pourri comme impl !
		setlocale(LC_TIME, self::$locales[Config::$LOCALE]) ;
		ClassLoader::add(TEMPLE_LOCALE_PATH . Config::$LOCALE . DIRECTORY_SEPARATOR, false) ;
		ClassLoader::add(CUSTOM_LOCALE_PATH . Config::$LOCALE . DIRECTORY_SEPARATOR, false, '.php') ;
		if(Config::$LOCALE !== Config::$DEFAULT_LOCALE) {
			ClassLoader::add(TEMPLE_LOCALE_PATH . Config::$DEFAULT_LOCALE . DIRECTORY_SEPARATOR, false) ;
			ClassLoader::add(CUSTOM_LOCALE_PATH . Config::$DEFAULT_LOCALE . DIRECTORY_SEPARATOR, false, '.php') ;
		}
		try {
			$c = new \ReflectionClass($cn);
			$i = $c->newInstance();
			return $i->createResponse() ;
		} catch (\Exception $e) {
			// TODO la failure n'est pas correcte si causée par createResponse
			$this->failure("Internal error while trying to create context '$vn'.", '', $e);
		}
	}

	public static function configure(array $viewNames, $defaultView = null) {
		if ($viewNames) {
			self::$viewNames = $viewNames;
		}
		if ($defaultView) {
			self::$defaultView = $defaultView;
		}
	}

}
