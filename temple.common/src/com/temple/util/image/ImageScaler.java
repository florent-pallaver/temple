package com.temple.util.image;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;

import com.temple.Module;
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
	ImageScaler(String imageMimeType) throws RuntimeException {
		super(Module.DEFAULT);
		final Iterator<ImageWriter> it = ImageIO.getImageWritersByMIMEType(imageMimeType);
		if (!it.hasNext()) {
			throw new RuntimeException("No ImageWriter available for type " + imageMimeType);
		}
		this.writer = it.next();
	}

	public void scale(File src, File dst, int dimension, ScaleStrategy m) {
		try {
			final BufferedImage srcImg = ImageIO.read(src);
			final RenderedImage dstImg = m.scale(srcImg, dimension);
			this.writer.setOutput(ImageIO.createImageOutputStream(dst));
			this.writer.write(dstImg);
		} catch (final Exception e) {
			this.logThrowable("Unable to scale image", e);
		}
	}

	@Override
	protected void finalize() {
		this.info("ImageScaler about to be garbage collected");
		this.writer.dispose();
	}
}
