package com.temple.util.file.media.stream;

public interface VideoStreamMetadata extends StreamMetadata {

	short getWidth();

	short getHeight();

	String getSampleAspectRatio();

	String getDisplayAspectRatio();

	String getRFrameRate();
}
