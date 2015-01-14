package com.temple.util.file.media;

import java.io.Serializable;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public interface Codec extends Serializable {

	/**
	 * TODOC
	 * 
	 * @return
	 */
	String getMimeType();

	/**
	 * TODOC
	 * 
	 * @return
	 */
	String getExtension();
}
