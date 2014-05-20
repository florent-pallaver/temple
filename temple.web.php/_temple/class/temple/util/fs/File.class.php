<?php

namespace temple\util\fs;

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
	
}
