package com.temple.util.file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public class LocalContent implements Content {

	private static final long serialVersionUID = 1L;

	private final File file;

	/**
	 * Constructor.
	 * TODOC
	 *
	 * @param file
	 */
	public LocalContent(File file) {
		this.file = file;
	}

	/**
	 * 
	 * @param filePath 
	 */
	public LocalContent(String filePath) {
		this(new File(filePath)) ;
	}
	
	@Override
	public final long getSize() {
		return this.file.length();
	}

	@Override
	public final boolean exists() {
		return this.file != null && this.file.exists();
	}

	@Override
	public final BufferedInputStream getInputStream() throws FileNotFoundException {
		return new BufferedInputStream(new FileInputStream(this.file));
	}
}
