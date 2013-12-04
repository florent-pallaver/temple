package com.temple.util.image;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public class WidthScaleStrategy implements ScaleStrategy {

	/**
	 * TODOC
	 */
	public static final ScaleStrategy instance = new WidthScaleStrategy();

	private WidthScaleStrategy() {}

	@Override
	public RenderedImage scale(BufferedImage srcImg, int dimension) throws IOException {
		final double ratio = dimension / (double) srcImg.getWidth();
		final BufferedImage dstImg = new BufferedImage(dimension, (int) Math.round(srcImg.getHeight() * ratio), srcImg.getType());
		final Graphics2D g = dstImg.createGraphics();
		g.drawRenderedImage(srcImg, AffineTransform.getScaleInstance(ratio, ratio));
		g.dispose();
		return dstImg;
	}
}
