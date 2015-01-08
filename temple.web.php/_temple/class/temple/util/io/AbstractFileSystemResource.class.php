<?php

namespace temple\util\io;

/**
 * Description of AbstractFileSystemResource
 *
 * @author florent
 */
abstract class AbstractFileSystemResource implements FileSystemResource {

	const LOGGER_NAME = 'FILE_SYSTEM' ;
	
	private $path ;
	
	/**
	 * @var Directory
	 */
	private $directory ;

	/**
	 * @var \temple\Logger
	 */
	protected $logger ;
	
	public function __construct($path, Directory $parent = null) {
		if(!$path) {
			throw new \temple\IllegalArgumentException('empty path given') ;
		}
		$dir = $parent ? $parent->getAbsolutePath() : '' ;
		if($path[0] == DIRECTORY_SEPARATOR || (DIRECTORY_SEPARATOR == '\\' && $path[0] == '/')) {
			$this->path = $dir . $path ;
		} else {
			$this->path = ($dir ? $dir : getcwd()) . DIRECTORY_SEPARATOR . $path ;
		}
		$this->directory = null ;
		$this->logger = \temple\Logger::getInstance(self::LOGGER_NAME) ;
	}

	public final function getAbsolutePath() {
		return $this->path ;
	}

	/**
	 * @return Directory
	 */
	protected final  function getDirectory() {
		if(!$this->directory) {
			$this->directory = new Directory(dirname($this->path)) ;
		}
		return $this->directory ;
	}
	
	public final function exists() {
		return file_exists($this->path) ;
	}

	public final function isReadable() {
		return is_readable($this->path);
	}
	
	public final function isWritable() {
		return is_writable($this->path) ;
	}
	
	public final function getLastModificationTime() {
		if($this->exists()) {
			$ts = filemtime($this->path);
			$t = $ts ? new \DateTime('@' . $ts) : null ;
		} else {
			$t = null ;
		}
		return $t ;
	}
	
	public function __toString() {
		return $this->path ;
	}
	
//	public function isAbsolute() {
//		return $this->parent == null && $path[0] == DIRECTORY_SEPARATOR ;
//	}
	
}
