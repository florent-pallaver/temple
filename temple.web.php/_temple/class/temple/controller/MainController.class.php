<?php

namespace temple\controller;

use temple\URL;
use temple\Logger;

/**
 * Description of MainController
 *
 * @author florent
 */
final class MainController extends AbstractRequestController {

	use \temple\Singleton;

	public static $namespace = 'controller';
	private static $suffix = 'Controller';
	private static $defaultView = 'home';
	private static $defaultAction = 'createResponse';
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
		$viewName = $this->getStringGetParam(URL::VIEW_PARAMETER, self::$defaultEmpty);
		if ($viewName) {
			if (array_search($viewName, self::$viewNames) !== false) {
				$vn = &$viewName;
			}
		}
		$cn = self::$namespace . '\\' . strtoupper($vn{0}) . substr($vn, 1) . self::$suffix;
		try {
			$c = new \ReflectionClass($cn);
			$i = $c->newInstance();
		} catch (\Exception $e) {
			$this->failure("An error occured while trying to create context '$vn'.", '', $e);
		}
		$an = _dif($this->getStringGetParam(URL::ACTION_PARAMETER, self::$defaultEmpty), self::$defaultAction);
        $ee = null ;
		try {
			if(strpos($an, '__') !== 0) { // no magic methods call
				$m = $c->getMethod($an);
				if ($m->isPublic() && !$m->isStatic()) {
					return $m->invoke($i) ;
				}
			}
		} catch (\Exception $e) {
			\temple\ExceptionHandler::log($e) ;
            $ee = $e ;
		}
		$this->failure("An error occured while trying to run action '$an' within context '$vn'.", '', $ee) ;
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
