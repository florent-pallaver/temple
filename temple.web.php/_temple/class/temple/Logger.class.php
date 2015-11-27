<?php

namespace temple;

/**
 * TODOC
 * @author florent
 */
final class Logger {

	const DEFAULT_NAME = '_GLOBAL';
	const LEVEL_FINEST = 1;
	const LEVEL_FINER = 2;
	const LEVEL_FINE = 3;
	const LEVEL_DEBUG = 4;
	const LEVEL_CONFIG = 5;
	const LEVEL_INFO = 6;
	const LEVEL_WARNING = 8;
	const LEVEL_SEVERE = 9;
	const LEVEL_FATAL = 10;

	private static $requestIdBytesCount = 12;
	private static $requestId;
	private static $levels = [self::LEVEL_FINEST => 'FINEST', self::LEVEL_FINER => 'FINER', self::LEVEL_FINE => 'FINE', self::LEVEL_DEBUG => 'DEBUG', self::LEVEL_CONFIG => 'CONFIG', self::LEVEL_INFO => 'INFO', self::LEVEL_WARNING => 'WARNING', self::LEVEL_SEVERE => 'SEVERE', self::LEVEL_FATAL => 'FATAL'];

	/**
	 * @var number $MIN_LOG_LEVEL - the mininimum log level, LEVEL_CONFIG by default.
	 */
	public static $MIN_LOG_LEVEL = self::LEVEL_CONFIG;
	private static $instances = [];
	private $name;
	/**
	 *
	 * @var resource | boolean
	 */
	private $logFile;

	private function __construct($name) {
		$this->name = $name;
		$this->logFile = fopen(TEMPLE_ROOT_PATH . 'log.log', 'a');
	}

	/**
	 * 
	 * @param type $level
	 * @return boolean
	 */
	private function isLoggable($level) {
		return !$level || $level >= self::$MIN_LOG_LEVEL ;
	} 
	
	public function isFinestLoggable() {
		return self::LEVEL_FINEST >= self::$MIN_LOG_LEVEL ;
	}
	public function isFinerLoggable() {
		return self::LEVEL_FINER >= self::$MIN_LOG_LEVEL ;
	}
	public function isFineLoggable() {
		return self::LEVEL_FINE >= self::$MIN_LOG_LEVEL ;
	}
	public function isDebugLoggable() {
		return self::LEVEL_DEBUG >= self::$MIN_LOG_LEVEL ;
	}
	public function isConfigLoggable() {
		return self::LEVEL_CONFIG >= self::$MIN_LOG_LEVEL ;
	}
	public function isInfoLoggable() {
		return self::LEVEL_INFO >= self::$MIN_LOG_LEVEL ;
	}
	public function isWarningLoggable() {
		return self::LEVEL_WARNING >= self::$MIN_LOG_LEVEL ;
	}
	public function isSevereLoggable() {
		return self::LEVEL_SEVERE>= self::$MIN_LOG_LEVEL ;
	}
	public function isFatalLoggable() {
		return self::LEVEL_FATAL>= self::$MIN_LOG_LEVEL ;
	}
	
	private function _log($str, $level) {
		if ($this->logFile && $this->isLoggable($level)) {
			fwrite($this->logFile, '<' . self::$requestId . '>');
			fwrite($this->logFile, '<' . $this->name . '>');
			if($level) {
				fwrite($this->logFile, '<' . _iod(self::$levels, $level, $level) . '> ');
			}
			fwrite($this->logFile, $str);
			fwrite($this->logFile, PHP_EOL);
		}
	}

	public function __destruct() {
		if($this->logFile) {
			fclose($this->logFile);
		}
	}

	/**
	 * Logs the given string, if the log level <strong>greater than or equal to</strong> $MIN_LOG_LEVEL.
	 *
	 * @param string $str - the string to log.
	 * @param number $level - the log level, 5 by default.
	 * @see $MIN_LOG_LEVEL
	 */
	public function log($str, $level = self::LEVEL_INFO) {
		$this->_log($str, $level);
	}

	public function finest($str) {
		$this->log($str, self::LEVEL_FINEST);
	}

	public function finer($str) {
		$this->log($str, self::LEVEL_FINER);
	}

	public function fine($str) {
		$this->log($str, self::LEVEL_FINE);
	}

	public function debug($str) {
		$this->log($str, self::LEVEL_DEBUG);
	}

	public function config($str) {
		$this->log($str, self::LEVEL_CONFIG);
	}

	public function info($str) {
		$this->log($str, self::LEVEL_INFO);
	}

	public function warning($str) {
		$this->log($str, self::LEVEL_WARNING);
	}

	public function severe($str) {
		$this->log($str, self::LEVEL_SEVERE);
	}

	public function fatal($str) {
		$this->log($str, self::LEVEL_FATAL);
	}

	/**
	 * 
	 * @param string $name
	 * @return Logger
	 */
	public static function getInstance($name = self::DEFAULT_NAME) {
		if (!self::$requestId) {
			self::$requestId = bin2hex(openssl_random_pseudo_bytes(self::$requestIdBytesCount));
		}
		if (!isset(self::$instances[$name])) {
			self::$instances[$name] = new Logger($name);
		}
		return self::$instances[$name];
	}

	public static function logRequest() {
		$method = $_SERVER['REQUEST_METHOD'];
		$query = $_SERVER['REQUEST_URI'];
		$ip = $_SERVER['REMOTE_ADDR'];
		$date = new \DateTime();
		$str = "$method $query from $ip at " . $date->format('Y-m-d H:i:s');
		self::getInstance()->_log($str, 0);
	}

	public static function logDuration($label) {
		self::getInstance()->_log(sprintf('%s : %s ms', $label, 1000 * (microtime(true) - TEMPLE_START_TS)), 0);
	}
}
