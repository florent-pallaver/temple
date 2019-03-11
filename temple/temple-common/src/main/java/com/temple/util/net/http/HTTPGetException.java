package com.temple.util.net.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.temple.LocalizedTempleException;

public class HTTPGetException extends LocalizedTempleException {

	private static final long serialVersionUID = 1L;

	public HTTPGetException(String url, IOException exception) {
		super(String.format("Get %s failed", url), exception);
	}

	public HTTPGetException(URL url, IOException exception) {
		this(url.toString(), exception);
	}

	public HTTPGetException(HttpURLConnection con, IOException exception) {
		this(con.getURL(), exception);
	}

	public HTTPGetException(HttpURLConnection con, int statusCode) {
		super(String.format("GET %s failed : error %d", con.getURL(), statusCode));
	}
	
}
