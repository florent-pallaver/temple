package com.temple.util.process;

import java.io.File;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FileTransferProgress extends AbstractProgress {

	private static final long serialVersionUID = 1L;

	@XmlElement
	private File destinationFile;

	protected FileTransferProgress() {}

	public FileTransferProgress(File destinationFile) {
		super();
		this.destinationFile = Objects.requireNonNull(destinationFile);
	}

	public File getDestinationFile() {
		return this.destinationFile;
	}

	@XmlElement
	@Override
	public long getProcessed() {
		return this.destinationFile.canRead() ? this.destinationFile.length() : 0;
	}

	@Override
	public void incrementProcessed(long unitAmount) {}

}
