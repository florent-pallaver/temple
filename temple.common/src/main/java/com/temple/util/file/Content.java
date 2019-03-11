package com.temple.util.file;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public interface Content extends Serializable {

	/**
	 * TODOC
	 *
	 * @return
	 */
	long getSize();

	/**
	 * TODOC
	 *
	 * @return
	 */
	boolean exists();

	/**
	 * TODOC
	 *
	 * @return
	 * @throws FileNotFoundException
	 */
	BufferedInputStream getInputStream() throws FileNotFoundException;
}
