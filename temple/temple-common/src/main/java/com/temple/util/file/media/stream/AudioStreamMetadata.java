package com.temple.util.file.media.stream;

public interface AudioStreamMetadata extends StreamMetadata {

	int getChannelsCount();

	Long getSampleRate();
}
