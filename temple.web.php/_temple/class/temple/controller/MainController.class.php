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

	public static $locale = 'en' ;
	public static $defaultLocale = 'en' ;
	public static $namespace = 'controller';
	private static $suffix = 'Controller';
	private static $defaultView = 'home';
	private static $viewNames = [];

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
		$cn = self::$namespace . '\\' . ($a ? ($vn . '\\'. strtoupper($a{0}) . substr($a, 1)) : (strtoupper($vn{0}) . substr($vn, 1))) . self::$suffix;
		// specify locale before creating response and causing any exception
		// FIXME un peu pourri comme impl !
		ClassLoader::add(TEMPLE_LOCALE_PATH . self::$locale . DIRECTORY_SEPARATOR, false) ;
		ClassLoader::add(CUSTOM_LOCALE_PATH . self::$locale . DIRECTORY_SEPARATOR, false, '.php') ;
		if(self::$locale !== self::$defaultLocale) {
			ClassLoader::add(TEMPLE_LOCALE_PATH . self::$defaultLocale . DIRECTORY_SEPARATOR, false) ;
			ClassLoader::add(CUSTOM_LOCALE_PATH . self::$defaultLocale . DIRECTORY_SEPARATOR, false, '.php') ;
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
