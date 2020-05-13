<?php

namespace temple\data;

use temple\util\io\File ;
use temple\util\Crypt ;

/**
 * Description of Cache
 *
 * @author florent
 */
final class Cache {

	use \temple\LazySingleton ;
	
	const LOGGER_NAME = '_FileCache' ;
	
	/**
	 * @var string the cache folder (ends with /)
	 */
	public static $FOLDER ;
	
	private static $indexFileName = '_cacheIndex' ;
		
	private $index ;
	
	private $data ;
	
	private function __construct() {
		$this->index = $this->readCacheFile(self::$indexFileName, []);
		$this->data = [];
	}
	
	public function clear() {
		$this->data = [] ;
		foreach($this->index as $dk) {
			$f = new File(self::$FOLDER . $dk) ;
			if($f->exists()) {
				$f->delete() ;
			}
		}
		$this->index = [] ;
		$this->writeCacheFile(self::$indexFileName, $this->index) ;
	}
	
	public function get($key) {
		$d = null ;
		if(isset($this->index[$key])) {
			$dk = &$this->index[$key] ;
			if(!array_key_exists($dk, $this->data)) {
				$this->data[$dk] = $this->readCacheFile($dk) ;
			}
			$d = $this->data[$dk] ;
		} else {
			\temple\Logger::getInstance(self::LOGGER_NAME)->info('Undefined key ' . $key) ;
		}
		return $d ;
	}
	
	public function put($key, $value) {
		if(isset($this->index[$key])) {
			$dk = &$this->index[$key] ;
		} else {
			$dk = null;
			// check if file exists
			do {
				$dk = Crypt::randomBytes(16);
				$f = new File(self::$FOLDER . $dk) ;
			} while ($f->exists()) ;
			$this->index[$key] = $dk ;
			$this->writeCacheFile(self::$indexFileName, $this->index) ;
		}
		$this->writeCacheFile($dk, $value) ;
		$this->data[$dk] = $value ;
	}
	
	private function readCacheFile($path, $default = null) {
		$f = new File(self::$FOLDER . $path) ;
		$d = $default ;
		if($f->exists()) {
			\temple\Logger::getInstance(self::LOGGER_NAME)->debug('Loading cache file ' . $f) ;
			return unserialize($f->getContent()) ;
//		} elseif($default !== null) {
//			$this->writeCacheFile($path, $default) ;
		} else {
			\temple\Logger::getInstance(self::LOGGER_NAME)->info('trying to read not existing file ' . $f) ;
		}
		return $d ;
	}
	
	private function writeCacheFile($path, $value) {
		$f = new File(self::$FOLDER . $path) ;
		$f->putContent(serialize($value), false) ;
	}

	private static function _init() {
		self::$FOLDER = CUSTOM_RESOURCE_PATH . 'cache/' ;
		
	}
}
