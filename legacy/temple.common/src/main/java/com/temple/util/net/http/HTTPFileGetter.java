package com.temple.util.net.http;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;

import com.temple.util.net.Domain;

public final class HTTPFileGetter extends AbstractHTTPGetter<File> {

	private static final long serialVersionUID = 1L;

	private final File downloadFolder;

	public HTTPFileGetter(Domain<?> domain, File downloadFolder) {
		super(domain);
		this.downloadFolder = downloadFolder;
	}

	@Override
	protected File getFile0(HttpURLConnection con, String path) throws IOException {
		return HTTPGetter.downloadFile(con, this.downloadFolder);
	}
}
