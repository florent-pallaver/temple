package com.temple.util.media.image;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public interface ScaleStrategy {

	/**
	 * TODOC
	 * 
	 * @param srcImg
	 * @param dimension
	 * @return
	 * @throws IOException
	 */
	RenderedImage scale(BufferedImage srcImg, int dimension) throws IOException;
}
