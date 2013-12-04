package com.temple.util.image;

import java.io.File;

import javax.activation.FileTypeMap;

import com.temple.Module;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public enum ImageType {
	PROGRESSIVE_JPEG("image/pjpeg"),
	JPEG("image/jpeg"),
	PNG("image/png"),
	SVG("image/svg+xml"),
	GIF("image/gif"),
	TIFF("image/tiff"),
	BMP("image/x-bmp");

	private static final FileTypeMap defaultFileTypeMap = FileTypeMap.getDefaultFileTypeMap();

	public final String mimeType;

	private ImageType(String mimeType) {
		this.mimeType = mimeType;
	}

	/**
	 * TODOC
	 * 
	 * @param f
	 * @return
	 */
	public static ImageType findType(File f) {
		return ImageType.findType(ImageType.defaultFileTypeMap.getContentType(f));
	}

	/**
	 * TODOC
	 * 
	 * @param mft
	 * @return
	 */
	public static ImageType findType(String mft) {
		for (final ImageType it : ImageType.values()) {
			if (it.mimeType.equals(mft)) {
				return it;
			}
		}
		Module.DEFAULT.logger.info(mft + " does not seems to be an MIME image type");
		return null;
	}

	/**
	 * TODOC
	 * 
	 * @return
	 */
	public ImageDataReader getImageReader() {
		return new ImageDataReader(this.mimeType);
	}

	/**
	 * TODOC
	 * 
	 * @return
	 */
	public ImageScaler getImageScaler() {
		return new ImageScaler(this.mimeType);
	}
}
