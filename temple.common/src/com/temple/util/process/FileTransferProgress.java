package com.temple.util.process;

import java.io.File;
import java.util.Objects;

public class FileTransferProgress extends AbstractProgress {

	private static final long serialVersionUID = 1L;

	private File destinationFile ;

	public FileTransferProgress(File destinationFile) {
		super();
		this.destinationFile = Objects.requireNonNull(destinationFile);
	}

	public File getDestinationFile() {
		return this.destinationFile;
	}

	@Override
	public long getProcessed() {
		return this.destinationFile.canRead() ? this.destinationFile.length() : 0;
	}

	@Override
	public void incrementProcessed(long unitAmount) {
	}

}
