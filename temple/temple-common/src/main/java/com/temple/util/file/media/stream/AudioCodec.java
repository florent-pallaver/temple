package com.temple.util.file.media.stream;

import com.temple.util.file.media.Codec;

public enum AudioCodec implements Codec {
	MP3("audio/mpeg", ".mp3");

	public final String mimeType;

	public final String extension;

	private AudioCodec(String mimeType, String extension) {
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
