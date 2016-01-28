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
	public static function log(Exception $e, $logTrace = true) {
		$l = Logger::getInstance() ;
		$l->log($e->getMessage(), self::$EXCEPTION_LOG_LEVEL) ;
		if($logTrace) {
			$l->log($e->getTraceAsString(), self::$EXCEPTION_LOG_LEVEL) ;
			if($e->getPrevious()) {
				self::log($e->getPrevious()) ;
			}
		}
	}
	
	/**
	 * 
	 * @param Exception $e
	 */
	public static function handle(Exception $e) {
		self::log($e) ;
		http_response_code(500) ;
		$v = new view\FailureView($e) ;
		$v->render() ;
	}

}
