<?php

namespace temple\util\fs;

/**
 * Description of Directory
 *
 * @author florent
 */
class Directory extends AbstractFileSystemResource {

	public function create() {
		if($this->exists()) {
			if(!is_dir($this->getAbsolutePath())) {
				throw new ResourceExistsException($this) ;
			}
		} else {
			$parent = $this->getDirectory() ;
			$parent->create() ;
			if($parent->isWritable()) {
				if(!mkdir($this->getAbsolutePath())) {
					throw new ResourceCreationException($this, error_get_last()['message']) ;
				}
			} else {
				throw new ResourceAccessException($parent) ;
			}
		} 
	}
	
}
