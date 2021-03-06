<?php

namespace temple\util\io;

interface FileSystemResource {
    /**
     * @return boolean true if this resource is absolute (ie has no parent directory), false otherwise.
     */
//	function isAbsolute() ;

    /**
     * Creates this file system resource and ensures every underlying resources are created as well.
     * @throws IOException if the creation of this resource fails
     */
    function create();

    /**
     * @return boolean
     */
    function exists();

    /**
     * @return boolean
     */
    function isReadable();

    /**
     * @return boolean
     */
    function isWritable();

    /**
     * @return \DateTime
     */
    function getLastModificationTime();

    /**
     * @return Directory the parent directory of this file system resource, null if it has no parent.
     */
    function getDirectory();

    /**
     * @return string - the absolute path of this resource.
     */
    function getAbsolutePath();

    /**
     * Deletes this file system resource.
     * 
     * @throws IOException if the deletion of this resource fails
     */
    function delete();
}
