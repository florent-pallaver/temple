package com.temple.util.file.media.stream;

import com.temple.Module;
import com.temple.util.file.media.MediaException;
import java.io.File;
import java.io.Serializable;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public class VideoStreamException extends MediaException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * TODOC
	 *
	 * @param file
	 */
	public VideoStreamException(File file) {
		super(new Serializable[]{ file }, Module.DEFAULT);
	}
}
