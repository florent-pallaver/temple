package com.temple.util.file.media.stream;

public interface VideoMetadata extends StreamMetadata {

	VideoStreamMetadata getVideoStreamMetadata();

	AudioStreamMetadata getAudioStreamMetadata();
}
