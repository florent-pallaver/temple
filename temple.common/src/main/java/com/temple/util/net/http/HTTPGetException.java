package com.temple.util.net.http;

import java.io.IOException;
import java.net.HttpURLConnection;

import com.temple.LocalizedTempleException;

public class HTTPGetException extends LocalizedTempleException {

	private static final long serialVersionUID = 1L;

	private final int statusCode;

	// private final Map<String, List<String>> headerFields;

	private final HttpURLConnection connection;

	public HTTPGetException(HttpURLConnection con, IOException exception) {
		super(exception);
		this.connection = con;
		this.statusCode = -1;
	}

	public HTTPGetException(HttpURLConnection con, int statusCode) {
		super(String.format("GET %s failed : error %d", con.getURL(), statusCode));
		this.statusCode = statusCode;
		this.connection = con;
	}

	public final int getStatusCode() {
		return this.statusCode;
	}

	public final HttpURLConnection getConnection() {
		return this.connection;
	}

	// public final Set<String> getHeaderKeys() {
	// return this.headerFields.keySet();
	// }
	//
	// public final List<String> getHeaderField(String headerKey) {
	// return this.headerFields.get(headerKey);
	// }

}
