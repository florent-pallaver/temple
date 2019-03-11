package com.temple.util.file.media.stream;

import com.temple.util.file.media.Codec;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public enum VideoCodec implements Codec {
	H264("video/mp4", ".mp4"),
	VP8("video/webm", ".webm"),
	THEORA("video/ogg", ".ogv");

	/**
	 * TODOC
	 */
	public final String mimeType;

	/**
	 * TODOC
	 */
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
