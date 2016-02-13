package com.temple.util.file.media.image;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;

import com.temple.util.AbstractLogger;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public class ImageScaler extends AbstractLogger {

	final ImageWriter writer;

	/**
	 * Constructor.
	 * TODOC
	 *
	 * @param imageMimeType
	 * @throws RuntimeException
	 */
	ImageScaler(ImageCodec ic) throws RuntimeException {
		super();
		this.writer = ic.getImageWriter();
	}

	public boolean scale(File src, File dst, int dimension, ScaleStrategy m) {
		boolean scaled = false;
		try {
			final BufferedImage srcImg = ImageIO.read(src);
			final RenderedImage dstImg = m.scale(srcImg, dimension);
			this.writer.setOutput(ImageIO.createImageOutputStream(dst));
			this.writer.write(dstImg);
			scaled = true ;
		} catch (final Exception e) {
			this.thrown("Unable to scale image", e);
		}
		return scaled ;
	}

	@Override
	protected void finalize() throws Throwable {
		if (this.isDebugLoggable()) {
			this.debug("ImageScaler about to be garbage collected");
		}
		this.writer.dispose();
		super.finalize();
	}
}
