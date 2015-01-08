package com.temple.util.media.stream;

import java.io.Serializable;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public interface StreamMetadata extends Serializable {

	/**
	 * @return the codec name
	 */
	String getCodecName();

	/**
	 * @return the codec long name
	 */
	String getCodecLongName();

	/**
	 * @return the bit rate
	 */
	Integer getBitRate();

	/**
	 * @return the duration
	 */
	Double getPreciseDuration();

	/**
	 * TODOC
	 *
	 * @return
	 */
	Integer getDuration();

	/**
	 * @return the size
	 */
	Integer getSize();
}
