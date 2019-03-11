package com.temple.util.file.media;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public enum TextCodec implements Codec {
	TEXT("text/plain", ".txt"),
	HTML("text/html", ".html");

	/**
	 * TODOC
	 */
	public final String mimeType;

	/**
	 * TODOC
	 */
	public final String extension;

	private TextCodec(String mimeType, String extension) {
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
