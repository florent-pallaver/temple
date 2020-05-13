package com.temple.util.file;

import java.io.File;

/**
 *
 * @author flominou
 */
public interface MimeTypeHelper {
	
	/**
	 * Finds the MIME type of a file.
	 * 
	 * @param f a file
	 * @return the MIME type of the given file, <code>null</code> if none found
	 */
	String findMimeType(File f) ;
	
}
