package com.temple.util.media.image;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;

/**
 * Implementation of {@link ScaleStrategy}. <br>
 * This implementation resizes an image (preserving its dimension ratio) only if one of its dimension is greater than
 * the given target dimension. If not it just returns the given image.
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public class MaxDimensionScaleStrategy implements ScaleStrategy {

	/**
	 * Sole instance of {@link MaxDimensionScaleStrategy}.
	 */
	public static final ScaleStrategy instance = new MaxDimensionScaleStrategy();

	private MaxDimensionScaleStrategy() {}

	@Override
	public RenderedImage scale(BufferedImage srcImg, int dimension) throws IOException {
		final double width = srcImg.getWidth();
		final double height = srcImg.getHeight();
		final BufferedImage dstImg;
		if (width > dimension || height > dimension) {
			final double ratio;
			final int type = srcImg.getType();
			if (width > height) {
				ratio = dimension / width;
				dstImg = new BufferedImage(dimension, (int) Math.round(height * ratio), type);
			} else {
				ratio = dimension / height;
				dstImg = new BufferedImage((int) Math.round(width * ratio), dimension, type);
			}
			final Graphics2D g = dstImg.createGraphics();
			g.drawRenderedImage(srcImg, AffineTransform.getScaleInstance(ratio, ratio));
			g.dispose();
		} else {
			dstImg = srcImg;
		}
		return dstImg;
	}
}
