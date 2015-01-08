package com.temple.util.media.stream;

import com.temple.util.media.Codec;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public enum AudioCodec implements Codec {
	MP3("audio/mpeg", ".mp3");

	/**
	 * TODOC
	 */
	public final String mimeType;

	/**
	 * TODOC
	 */
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
