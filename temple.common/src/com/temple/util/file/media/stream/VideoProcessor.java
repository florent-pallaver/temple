package com.temple.util.file.media.stream;

import java.io.File;
import java.io.Serializable;

import com.temple.util.file.media.MediaIndex;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public interface VideoProcessor {

	/**
	 * TODOC
	 * main preview is appended horizontally if width >= height, vertically otherwise.
	 *
	 * @param ownerId
	 * @param videoId
	 * @param previewIndex
	 * @param videoFile
	 * @param count
	 * @param maxDimension
	 * @throws VideoStreamException
	 */
	void createPreviews(Serializable ownerId, Serializable videoId, MediaIndex previewIndex, File videoFile, int count, int maxDimension)
			throws VideoStreamException;

	void convertVideo(Serializable ownerId, Serializable videoId, MediaIndex destVideoIndex, int maxDimension);
}
