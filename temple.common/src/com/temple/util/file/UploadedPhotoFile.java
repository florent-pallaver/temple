package com.temple.util.file;

import com.temple.util.file.media.image.ImageCodec;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public interface UploadedPhotoFile extends UploadedFile {

	/**
	 * TODOC
	 *
	 * @return
	 */
	ImageCodec getType();
}
