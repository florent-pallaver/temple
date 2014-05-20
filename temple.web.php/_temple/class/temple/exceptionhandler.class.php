<?php

namespace temple ;

use Exception ;

final class ExceptionHandler {

	/**
	 * @var number the default log level used to log exception details
	 */
	public static $EXCEPTION_LOG_LEVEL = 9 ;

	private function __construct() {}

	/**
	 * 
	 * @param Exception $e
	 */
	public static function log(Exception $e) {
		$l = Logger::getInstance() ;
		$l->log($_SERVER['REQUEST_URI'], self::$EXCEPTION_LOG_LEVEL) ;
		$l->log($e->getMessage(), self::$EXCEPTION_LOG_LEVEL) ;
		$l->log($e->getTraceAsString(), self::$EXCEPTION_LOG_LEVEL) ;
	}
	
	/**
	 * 
	 * @param \Exception $e
	 */
	public static function handle(Exception $e) {
		self::log($e) ;
		$v = new view\FailureView($e) ;
		$v->render() ;
	}

}
