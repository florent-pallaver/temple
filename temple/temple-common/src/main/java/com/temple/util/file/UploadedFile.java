package com.temple.util.file;

import java.io.File;
import java.io.Serializable;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public interface UploadedFile extends Serializable {

	/**
	 * TODOC
	 *
	 * @return
	 */
	String getName();

	/**
	 * TODOC
	 * @return 
	 */
	String getExtension() ;
	
	/**
	 * TODOC
	 *
	 * @return
	 */
	File getFile();

	/**
	 * TODOC
	 *
	 * @param destFile
	 * @return <code>true</code> if the file as been written to destFile, <code>false</code> otherwise.
	 */
	boolean writeTo(File destFile);
}
