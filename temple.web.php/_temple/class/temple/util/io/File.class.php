<?php

namespace temple\util\io;

/**
 * Description of File
 *
 * @author florent
 */
class File extends AbstractFileSystemResource {

	public function create() {
		if($this->exists()) {
			if(is_dir($this->getAbsolutePath())) {
				throw new ResourceExistsException($this) ;
			}
		} else {
			$parent = $this->getDirectory() ;
			$parent->create() ;
			if($parent->isWritable()) {
				$f = fopen($this->getAbsolutePath(), 'c') ;
				if($f) {
					fclose($f) ;
				} else {
					throw new ResourceCreationException($this, error_get_last()['message']) ;
				}
			} else {
				throw new ResourceAccessException($parent) ;
			}
		}
	}

	public function getContent() {
		if($this->isReadable()) {
			return file_get_contents($this) ;
		}
		throw new ResourceAccessException($this);
	}
	
	public function putContent($content, $append = true) {
		$this->create() ;
		if($this->isWritable()) {
			return file_put_contents($this, $content, $append ? FILE_APPEND : 0) ;
		}
		throw new ResourceAccessException($this) ;
	}
	
	public function delete() {
		if(!unlink($this->getAbsolutePath())) {
			throw new ResourceAccessException($this) ;
		} 
	}
	
	/**
	 * 
	 * @return string the mime type as return by finfo
	 * @throws ResourceAccessException
	 */
	public function getMimeType() {
		if(!$this->isReadable()) {
			throw new ResourceAccessException($this) ;
		}
		$fi = new \finfo(FILEINFO_MIME_TYPE) ;
		$mt = $fi->file($this->getAbsolutePath()) ;
		return $mt ;
	}
	
}
