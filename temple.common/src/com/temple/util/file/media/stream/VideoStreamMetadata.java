package com.temple.util.file.media.stream;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public interface VideoStreamMetadata extends StreamMetadata {

	/**
	 * TODOC
	 *
	 * @return
	 */
	short getWidth();

	/**
	 * TODOC
	 *
	 * @return
	 */
	short getHeight();

	/**
	 * @return the sample_aspect_ratio
	 */
	String getSampleAspectRatio();

	/**
	 * @return the display_aspect_ratio
	 */
	String getDisplayAspectRatio();

	/**
	 * @return the r_frame_rate
	 */
	String getRFrameRate();
}
