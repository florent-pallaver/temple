<?php

namespace temple\controller;

use Exception;
use temple\data\Messages;

abstract class AbstractController extends \temple\WithLogger implements Controller {

	public function __construct() {
		parent::__construct();
	}

	public function getLocales() {
		$c = static::class . Config::CONTROLLER_LOCALE_CLASS_SUFFIX;
		return $c::$all;
	}

	/**
	 * Logs the given exception
	 * @param Exception $e
	 */
	protected final function ignore(Exception $e) {
		\temple\ExceptionHandler::log($e);
	}

	protected final function success($msg) {
		Messages::getInstance()->success($msg);
	}

	protected final function info($msg) {
		Messages::getInstance()->info($msg);
	}

	protected final function warning($msg, Exception $e = null) {
		Messages::getInstance()->warning($msg, $e);
	}

	protected final function error($msg, Exception $e = null) {
		Messages::getInstance()->error($msg, $e);
	}

}
