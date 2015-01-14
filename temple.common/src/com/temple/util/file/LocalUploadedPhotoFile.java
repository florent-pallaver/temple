package com.temple.util.file;

import java.io.File;

import com.temple.util.file.media.image.ImageCodec;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public class LocalUploadedPhotoFile extends LocalUploadedFile implements UploadedPhotoFile {

	private static final long serialVersionUID = 1L;

	private final ImageCodec type;

	/**
	 * Constructor.
	 */
	public LocalUploadedPhotoFile(String name, File pf, ImageCodec it) {
		super(name, pf);
		this.type = it;
	}

	@Override
	public ImageCodec getType() {
		return this.type;
	}
}
