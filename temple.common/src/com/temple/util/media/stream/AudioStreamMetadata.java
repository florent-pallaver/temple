package com.temple.util.media.stream;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public interface AudioStreamMetadata extends StreamMetadata {

	/**
	 * @return the channels count
	 */
	public int getChannels();

	/**
	 * @return the sample rate
	 */
	public Integer getSampleRate();
}
