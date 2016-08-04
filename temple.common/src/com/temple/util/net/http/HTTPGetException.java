package com.temple.util.net.http;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.temple.LocalizedTempleException;

/**
 *
 * @author flominou
 */
public class HTTPGetException extends LocalizedTempleException {

	private static final long serialVersionUID = 1L;

	private final int statusCode;

	private final Map<String, List<String>> headerFields;

	public HTTPGetException(Exception e) {
		this(e, -1, null, null);
	}

	public HTTPGetException(int statusCode) {
		this(statusCode, null);
	}

	public HTTPGetException(int statusCode, String message) {
		this(null, statusCode, message, null);
	}

	private HTTPGetException(Exception e, int sc, String msg, Map<String, List<String>> headerFields) {
		super(msg, e);
		this.statusCode = sc;
		this.headerFields = headerFields == null ? Collections.emptyMap() : headerFields;
	}

	public HTTPGetException(int statusCode, String msg, Map<String, List<String>> headerFields) {
		this(null, statusCode, msg, headerFields);
	}

	public final int getStatusCode() {
		return this.statusCode;
	}

	public final Set<String> getHeaderKeys() {
		return this.headerFields.keySet() ;
	}

	public final List<String> getHeaderField(String headerKey) {
		return this.headerFields.get(headerKey);
	}

}
