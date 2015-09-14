<?php

namespace temple\controller;

use Exception ;
use temple\data\Messages ;

/**
 * Description of AbstractController
 *
 * @author florent
 */
abstract class AbstractController extends \temple\WithLogger implements Controller {

	public function __construct() {
		parent::__construct() ;
	}

	protected final function success($msg) {
		Messages::getInstance()->success($msg) ;
	}

	protected final function info($msg) {
		Messages::getInstance()->info($msg) ;
	}

	protected final function warning($msg, Exception $e = null) {
		Messages::getInstance()->warning($msg, $e) ;
	}

	protected final function error($msg, Exception $e = null) {
		Messages::getInstance()->error($msg, $e) ;
	}

}
