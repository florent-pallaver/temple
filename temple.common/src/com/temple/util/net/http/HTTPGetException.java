package com.temple.util.net.http;

import com.temple.LocalizedTempleException;

/**
 * 
 * @author flominou
 */
public class HTTPGetException extends LocalizedTempleException {

	private static final long serialVersionUID = 1L;

	private final int statusCode;

	public HTTPGetException(Exception e) {
		this(e, -1, null);
	}

	public HTTPGetException(int statusCode) {
		this(statusCode, null);
	}

	public HTTPGetException(int statusCode, String message) {
		this(null, statusCode, message);
	}

	private HTTPGetException(Exception e, int sc, String msg) {
		super(msg, e);
		this.statusCode = sc;
	}

	/**
	 * TODOC
	 * @return
	 */
	public final int getStatusCode() {
		return this.statusCode;
	}
}
