<?php

namespace temple\controller;

use temple\URL;
use temple\Logger ;

/**
 * Description of MainController
 *
 * @author florent
 */
final class MainController extends AbstractRequestController {

	use \temple\Singleton;

	public static $namespace = 'controller' ;
	
	private static $suffix = 'Controller' ;
	
	private static $defaultView = 'home';

	private static $viewNames = [];

	public function getActionDescription() {
		return 'create requested web page';
	}

	public function createResponse() {
		$r = $this->getController()->createResponse() ;
		\temple\data\Session::getInstance()->end() ;
		if(!($r instanceof \temple\view\Renderable)) {
			Logger::getInstance()->fatal('The response returned was not an instance of \\temple\\view\\Renderable.') ;
			$this->failure() ;
		}
		return $r ;
	}
	
	/**
	 * 
	 * @param string $viewName
	 * @return Controller
	 */
	private function getController() {
		$vn = self::$defaultView;
		$rp = self::$namespace . '\\' ;
		$viewName = $this->getStringGetParam(URL::VIEW_PARAMETER, ['default' => '']);
		if ($viewName) {
			if (array_search($viewName, self::$viewNames) !== false) {
				$vn = &$viewName;
			}
		}
		$actionName = $this->getStringGetParam(URL::ACTION_PARAMETER, ['default' => '']);
		if($actionName) {
			$rp .= $vn . '\\' ;
			$vn = &$actionName ;
		}
		$cn = $rp . strtoupper($vn{0}) . substr($vn, 1) . self::$suffix ;
		try {
			$c = new \ReflectionClass($cn);
			$i = $c->newInstance() ;
		} catch (\Exception $e) {
			\temple\ExceptionHandler::log($e) ;
			if($actionName) {
				$i = new InvalidActionController() ;
			} else {
				$this->failure() ;
			}
		}
		return $i ;
	}

	public static function configure(array $viewNames, $defaultView = null) {
		if ($viewNames) {
			self::$viewNames = $viewNames;
		}
		if($defaultView) {
			self::$defaultView  = $defaultView ;
		}
	}

}
