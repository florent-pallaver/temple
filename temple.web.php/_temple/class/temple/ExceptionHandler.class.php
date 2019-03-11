<?php

namespace temple ;

use Throwable  ;

final class ExceptionHandler {

	/**
	 * @var number the default log level used to log exception details
	 */
	public static $EXCEPTION_LOG_LEVEL = 9 ;

	private function __construct() {}

	/**
	 * 
	 * @param Error $e
	 */
	public static function log(Throwable $e, $logTrace = true) {
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
	 * @param Error $e
	 */
	public static function handle(Throwable $e) {
		self::log($e) ;
		http_response_code(500) ;
		$v = new view\FailureView($e) ;
		$v->render() ;
	}

}
