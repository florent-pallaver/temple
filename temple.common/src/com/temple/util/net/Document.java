package com.temple.util.net;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;

import com.temple.Module;

/**
 * TODOC
 *
 * @author flominou
 */
public final class Document implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Domain<?> domain ;

	private final String path ;

	private final String content ;

	public Document(Domain<?> domain, String path, String content) {
		super();
		this.domain = domain;
		this.path = path;
		this.content = content;
	}

	/**
	 * @return the domain
	 */
	public Domain<?> getDomain() {
		return this.domain;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return this.path;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 * TODOC
	 * @param file
	 */
	public void writeTo(File file) {
		try(FileWriter fw = new FileWriter(file, false)) {
			fw.write(this.content);
		} catch (final IOException e) {
			Module.DEFAULT.logger.log(Level.SEVERE, "Unable to write document to " + file.getAbsolutePath(), e);
		}
	}

}
