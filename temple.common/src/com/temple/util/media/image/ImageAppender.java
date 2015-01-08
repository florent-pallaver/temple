package com.temple.util.media.image;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
public final class ImageAppender extends AbstractLogger {

	public static final ImageAppender instance = new ImageAppender();

	private ImageAppender() {
		super(Module.DEFAULT);
	}

	public void appendHorizontally(File[] images, File dest, ImageCodec destType) throws IOException {
		int dstWidth = 0;
		int dstHeight = 0;
		if (images != null && images.length > 1) {
			for (int i = images.length; i-- > 0;) {
				final File image = images[i];
				final int[] dim = ImageCodec.findType(image).getImageReader().getDimensions(image);
				dstWidth += dim[0];
				dstHeight = Math.max(dstHeight, dim[1]);
			}
			final BufferedImage dstImg = new BufferedImage(dstWidth, dstHeight, BufferedImage.TYPE_INT_RGB);
			final Graphics2D g = dstImg.createGraphics();
			for (int i = images.length; i-- > 0;) {
				final File image = images[i];
				final int[] dim = ImageCodec.findType(image).getImageReader().getDimensions(image);
				final int dx1 = dstWidth - dim[0];
				g.drawImage(ImageIO.read(image), dx1, 0, dstWidth, dim[1], 0, 0, dim[0], dim[1], null);
				dstWidth = dx1;
			}
			g.dispose();
			final ImageWriter iw = destType.getImageWriter();
			iw.setOutput(ImageIO.createImageOutputStream(dest));
			iw.write(dstImg);
			iw.dispose();
		}
	}

	public void appendVertically(File[] images, File dest, ImageCodec destType) throws IOException {
		int dstWidth = 0;
		int dstHeight = 0;
		if (images != null && images.length > 1) {
			for (int i = images.length; i-- > 0;) {
				final File image = images[i];
				final int[] dim = ImageCodec.findType(image).getImageReader().getDimensions(image);
				dstWidth = Math.max(dstWidth, dim[0]);
				dstHeight += dim[1];
			}
			final BufferedImage dstImg = new BufferedImage(dstWidth, dstHeight, BufferedImage.TYPE_INT_RGB);
			final Graphics2D g = dstImg.createGraphics();
			for (int i = images.length; i-- > 0;) {
				final File image = images[i];
				final int[] dim = ImageCodec.findType(image).getImageReader().getDimensions(image);
				final int dy1 = dstHeight - dim[1];
				g.drawImage(ImageIO.read(image), 0, dy1, dim[0], dstHeight, 0, 0, dim[0], dim[1], null);
				dstHeight = dy1;
			}
			g.dispose();
			final ImageWriter iw = destType.getImageWriter();
			iw.setOutput(ImageIO.createImageOutputStream(dest));
			iw.write(dstImg);
			iw.dispose();
		}
	}
}
