package com.temple.util.file.media.stream;

public interface VideoMetadata extends StreamMetadata {

	/**
	 * @return the videoStreamMetadata, never <code>null</code>
	 */
	VideoStreamMetadata getVideoStreamMetadata();

	/**
	 * @return the audioStreamMetadata, never <code>null</code>
	 */
	AudioStreamMetadata getAudioStreamMetadata();
}
