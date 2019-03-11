package com.temple.util.file.media.stream;

import com.temple.Module;
import com.temple.util.file.media.MediaException;
import java.io.File;
import java.io.Serializable;

public class NoVideoStreamException extends MediaException {

	private static final long serialVersionUID = 1L;

	public NoVideoStreamException(File file) {
		super(new Serializable[]{ file }, Module.DEFAULT);
	}
}
