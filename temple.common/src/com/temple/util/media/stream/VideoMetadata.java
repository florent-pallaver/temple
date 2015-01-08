package com.temple.util.media.stream;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public interface VideoMetadata extends StreamMetadata {

	/**
	 * @return the videoStreamMetadata
	 */
	VideoStreamMetadata getVideoStreamMetadata();

	/**
	 * @return the audioStreamMetadata
	 */
	AudioStreamMetadata getAudioStreamMetadata();
}
