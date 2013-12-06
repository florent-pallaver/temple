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
public class SquareScaleStrategy implements ScaleStrategy {

	/**
	 * TODOC
	 */
	public static final ScaleStrategy instance = new SquareScaleStrategy();

	private SquareScaleStrategy() {}

	@Override
	public RenderedImage scale(BufferedImage srcImg, int dimension) throws IOException {
		final double w = srcImg.getWidth();
		final double h = srcImg.getHeight();
		final int sw, sh, tx, ty;
		final double ratio;
		if (w > h) {
			ratio = dimension / h;
			sw = (int) Math.round(w * ratio);
			sh = dimension;
			tx = (sh - sw) / 2;
			ty = 0;
		} else {
			ratio = dimension / w;
			sw = dimension;
			sh = (int) Math.round(h * ratio);
			tx = 0;
			ty = (sw - sh) / 2;
		}
		final BufferedImage dstImg = new BufferedImage(dimension, dimension, srcImg.getType());
		final Graphics2D g = dstImg.createGraphics();
		g.translate(tx, ty);
		g.drawRenderedImage(srcImg, AffineTransform.getScaleInstance(ratio, ratio));
		g.dispose();
		return dstImg;
	}
}
