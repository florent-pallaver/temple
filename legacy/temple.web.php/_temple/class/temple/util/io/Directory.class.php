<?php

namespace temple\util\io;

/**
 * Description of Directory
 *
 * @author florent
 */
class Directory extends AbstractFileSystemResource {

	public function create() {
		if ($this->exists()) {
			if (!is_dir($this->getAbsolutePath())) {
				throw new ResourceExistsException($this);
			}
		} else {
			$parent = $this->getDirectory();
			$parent->create();
			if ($parent->isWritable()) {
				if (!mkdir($this->getAbsolutePath())) {
					throw new ResourceCreationException($this, error_get_last()['message']);
				}
			} else {
				throw new ResourceAccessException($parent);
			}
		}
	}

	public function delete() {
		if (!rmdir($this->getAbsolutePath())) {
			throw new ResourceAccessException($this);
		}
	}

	public function deleteRecursive() {
		foreach($this->getEntries() as $e) {
			if($e instanceof File) {
				$e->delete() ;
			} else {
				$e->deleteRecursive() ;
			}
		}
		$this->delete() ;
	}
	
	/**
	 * 
	 * @param int $order one of SCANDIR_SORT_NONE, SCANDIR_SORT_ASCENDING or SCANDIR_SORT_DESCENDING
	 * @return array
	 * @throws ResourceAccessException
	 */
	public function getEntries($order = SCANDIR_SORT_NONE) {
		if(!$this->isReadable()) {
			throw new ResourceAccessException($this) ;
		}
		$entries = [] ;
		$p = $this->getAbsolutePath() ;
		foreach(scandir($p, $order) as $e) {
			if($e != '.' && $e != '..') {
				$f = $p . '/' . $e ;
				$entries[] = is_dir($f) ? new Directory($e, $this) : new File($e, $this) ;
			}
		}
		return $entries ;
	}
	
}
