package com.temple.util.image;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import com.temple.Module;
import com.temple.util.AbstractLogger;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public class ImageDataReader extends AbstractLogger {

	final ImageReader reader;

	ImageDataReader(String imageMimeType) throws RuntimeException {
		super(Module.DEFAULT);
		final Iterator<ImageReader> it = ImageIO.getImageReadersByMIMEType(imageMimeType);
		if (!it.hasNext()) {
			throw new RuntimeException("No ImageReader available for type " + imageMimeType);
		}
		this.reader = it.next();
	}

	/**
	 * TODOC
	 * 
	 * @param image
	 * @return
	 */
	public int[] getDimensions(File image) {
		final int[] dim = { 0, 0 };
		this.info(image.getAbsolutePath());
		try (ImageInputStream iis = ImageIO.createImageInputStream(image)) {
			this.info(iis == null ? "null" : "Not null");
			this.reader.setInput(iis);
			dim[0] = this.reader.getWidth(0);
			dim[1] = this.reader.getHeight(0);
		} catch (final IOException e) {
			this.logThrowable("Unable to get dimension from photo", e);
		}
		return dim;
	}

	@Override
	protected void finalize() {
		this.info("ImageDataReader about to be garbage collected");
		this.reader.dispose();
	}
}
