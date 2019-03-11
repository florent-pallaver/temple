package com.temple.util.net;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;

import com.temple.Module;

public final class Document implements Serializable {

	private static final long serialVersionUID = 1L;

	private final HttpDomain<?> domain;

	private final String path;

	private final String content;

	public Document(HttpDomain<?> domain, String path, String content) {
		super();
		this.domain = domain;
		this.path = path;
		this.content = content;
	}

	public HttpDomain<?> getDomain() {
		return this.domain;
	}

	public String getPath() {
		return this.path;
	}

	public String getContent() {
		return this.content;
	}

	public void writeTo(File file) {
		try (FileWriter fw = new FileWriter(file, false)) {
			fw.write(this.content);
		} catch (final IOException e) {
			Module.DEFAULT.logger.log(Level.SEVERE, "Unable to write document to " + file.getAbsolutePath(), e);
		}
	}

}
