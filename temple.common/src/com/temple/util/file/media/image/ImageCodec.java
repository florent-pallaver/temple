package com.temple.util.file.media.image;

import java.io.File;
import java.util.Iterator;
import java.util.logging.Level;

import javax.activation.FileTypeMap;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;

import com.temple.Module;
import com.temple.util.file.media.Codec;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public enum ImageCodec implements Codec {
	PROGRESSIVE_JPEG("image/pjpeg", ".jpg"),
	JPEG("image/jpeg", ".jpg"),
	PNG("image/png", ".png"),
	SVG("image/svg+xml", ".svg"),
	GIF("image/gif", ".gif"),
	TIFF("image/tiff", ".tiff"),
	BMP("image/x-bmp", ".bmp");

	private static final FileTypeMap defaultFileTypeMap = FileTypeMap.getDefaultFileTypeMap();

	/**
	 * TODOC
	 */
	public final String mimeType;

	/**
	 * TODOC
	 */
	public final String extension;

	private ImageCodec(String mimeType, String extension) {
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

	/**
	 * TODOC
	 *
	 * @param f
	 * @return
	 */
	public static ImageCodec findType(File f) {
		return ImageCodec.findType(ImageCodec.defaultFileTypeMap.getContentType(f));
	}

	/**
	 * TODOC
	 *
	 * @param mft
	 * @return
	 */
	public static ImageCodec findType(String mft) {
		for (final ImageCodec it : ImageCodec.values()) {
			if (it.mimeType.equals(mft)) {
				return it;
			}
		}
		if (Module.DEFAULT.logger.isLoggable(Level.WARNING)) {
			Module.DEFAULT.logger.warning(mft + " does not seems to be a MIME image type");
		}
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
		return new ImageScaler(this);
	}

	ImageWriter getImageWriter() {
		final Iterator<ImageWriter> it = ImageIO.getImageWritersByMIMEType(this.mimeType);
		if (!it.hasNext()) {
			throw new RuntimeException("No ImageWriter available for type " + this.mimeType);
		}
		return it.next();
	}
}
