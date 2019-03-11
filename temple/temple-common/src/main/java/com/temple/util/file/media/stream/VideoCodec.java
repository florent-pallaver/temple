package com.temple.util.file.media.stream;

import com.temple.util.file.media.Codec;

public enum VideoCodec implements Codec {
	H264("video/mp4", ".mp4"),
	VP8("video/webm", ".webm"),
	THEORA("video/ogg", ".ogv");

	public final String mimeType;

	public final String extension;

	private VideoCodec(String mimeType, String extension) {
		this.mimeType = mimeType;
		this.extension = extension;
	}

	@Override
	public String getMimeType() {
		return this.mimeType;
	}

	@Override
	public String getExtension() {
		return this.extension;
	}
}
