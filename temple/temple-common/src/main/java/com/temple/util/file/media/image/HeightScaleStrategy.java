package com.temple.util.file.media.image;

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
public class HeightScaleStrategy implements ScaleStrategy {

	/**
	 * TODOC
	 */
	public static final ScaleStrategy instance = new HeightScaleStrategy();

	private HeightScaleStrategy() {}

	@Override
	public RenderedImage scale(BufferedImage srcImg, int dimension) throws IOException {
		final double ratio = dimension / (double) srcImg.getHeight();
		final BufferedImage dstImg = new BufferedImage((int) Math.round(srcImg.getWidth() * ratio), dimension, srcImg.getType());
		final Graphics2D g = dstImg.createGraphics();
		g.drawRenderedImage(srcImg, AffineTransform.getScaleInstance(ratio, ratio));
		g.dispose();
		return dstImg;
	}
}
