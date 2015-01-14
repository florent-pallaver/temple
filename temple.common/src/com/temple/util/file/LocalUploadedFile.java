package com.temple.util.file;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import com.temple.util.AbstractLogger;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public class LocalUploadedFile extends AbstractLogger implements UploadedFile {

	private static final long serialVersionUID = 1L;

	private final String name;

	private File file;

	/**
	 * Constructor.
	 * TODOC
	 *
	 * @param name
	 * @param pf
	 */
	public LocalUploadedFile(String name, File pf) {
		super();
		final String fileName = name.trim();
		final int endIndex = fileName.lastIndexOf('.');
		this.name = endIndex > 0 ? fileName.substring(0, endIndex) : fileName;
		this.file = pf;
	}

	@Override
	public final String getName() {
		return this.name;
	}

	@Override
	public final File getFile() {
		return this.file;
	}

	@Override
	public final boolean writeTo(File dest) {
		boolean b;
		final File parentFile = dest.getParentFile();
		if (!(parentFile.exists() || parentFile.mkdirs())) {
			this.warning("Could not create folders " + parentFile.getAbsolutePath());
		}
		final FileSystem fs = FileSystems.getDefault();
		final Path srcPath = fs.getPath(this.file.getAbsolutePath());
		final Path dstPath = fs.getPath(dest.getAbsolutePath());
		if (this.isDebugLoggable()) {
			this.debug("Moving " + srcPath + " to " + dstPath);
		}
		try {
			Files.move(srcPath, dstPath);
			this.file = dest;
			b = true;
		} catch (final Exception e) {
			b = false;
			this.throwable("unable to move " + this.file.getAbsolutePath() + " to " + dest.getAbsolutePath(), e);
		}
		return b;
	}
}
