package com.temple.util.file.media.stream;

public interface AudioStreamMetadata extends StreamMetadata {

	/**
	 * @return the channels count
	 */
	public int getChannels();

	public Integer getSampleRate();
}
