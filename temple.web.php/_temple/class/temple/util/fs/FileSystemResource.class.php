<?php

namespace temple\util\fs;

/**
 *
 * @author florent
 */
interface FileSystemResource {

	/**
	 * @return boolean true if this resource is absolute (ie has no parent directory), false otherwise.
	 */
//	function isAbsolute() ;
	
	/**
	 * Creates this file system resource and ensures every underlying resources are created as well.
	 * @throws FileSystemException if the creation of this resource fails
	 */
	function create() ;
	
	/**
	 * @return boolean
	 */
	function exists() ;
	
	/**
	 * @return boolean
	 */
	function isReadable() ;
	
	/**
	 * @return boolean
	 */
	function isWritable() ;
	
	/**
	 * @return \DateTime
	 */
	function getLastModificationTime() ;
	
	/**
	 * @return Directory the parent directory of this file system resource, null if it has no parent.
	 */
//	function getDirectory() ;
	
	/**
	 * @return string - the absolute path of this resource.
	 */
	function getAbsolutePath() ;
	
}
