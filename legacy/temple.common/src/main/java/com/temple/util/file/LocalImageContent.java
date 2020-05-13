package com.temple.util.file;

import java.io.File;

import com.temple.util.file.media.image.ImageCodec;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public final class LocalImageContent extends LocalContent implements ImageContent {

	private static final long serialVersionUID = 1L;

	private final ImageCodec type;

	/**
	 * Constructor.
	 * TODOC
	 *
	 * @param type
	 * @param file
	 */
	public LocalImageContent(ImageCodec type, File file) {
		super(file);
		this.type = type;
	}

	@Override
	public ImageCodec getType() {
		return this.type;
	}
}
