package com.temple.util.net.http;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class DefaultHTTPGetter implements HTTPGetter<File> {

	public static final int CONNECT_TIMEOUT = 6000; 
	
	private int readTimeout = DEFAULT_READ_TIMEOUT;
	
	@Override
	public Cookie getCookie() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File getFile(URL url) throws HTTPGetException {
		try {
			final URLConnection connection = url.openConnection();
			connection.setConnectTimeout(CONNECT_TIMEOUT);
			connection.setReadTimeout(this.readTimeout);
			connection.connect();
			try(final InputStream in = connection.getInputStream()) {
				final Path tempFile = Files.createTempFile("temple", UUID.randomUUID().toString());
				Files.copy(in, tempFile, StandardCopyOption.REPLACE_EXISTING);
				return tempFile.toFile();
			}
		} catch (IOException e) {
			throw new HTTPGetException(url, e);
		}
	}

	@Override
	public File getFile(String fileURL) throws HTTPGetException {
		try {
			return this.getFile(new URL(fileURL));
		} catch (MalformedURLException e) {
			throw new HTTPGetException(fileURL, e);
		}
	}

	@Override
	public int getReadTimeout() {
		return this.readTimeout;
	}

	@Override
	public void setReadTimeout(int timeout) {
		this.readTimeout = timeout;
	}
	
}
