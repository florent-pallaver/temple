package com.temple.util.net.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.util.Optional;
import java.util.zip.GZIPInputStream;

import com.temple.util.net.Document;
import com.temple.util.net.HttpDomain;

/**
 * Utility class to get document through HTTP or HTTPS protocol. To be
 * consistent, the same object should be used to get documents for and only for
 * one given session as the referrer and cookie are automatically refreshed.
 *
 * @author flominou
 */
public final class HTTPDocumentGetter extends AbstractHTTPGetter<Document> implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int BUFFER_SIZE = 8192; // in bytes

	public HTTPDocumentGetter(HttpDomain<?> domain) {
		super(domain);
	}

	@Override
	protected Document getFile0(HttpURLConnection con, String path) throws IOException {
		final StringBuilder sb = new StringBuilder();
		final char[] cbuf = new char[HTTPDocumentGetter.BUFFER_SIZE];
		try (BufferedReader reader = new BufferedReader(this.getReader(con))) {
			while (reader.read(cbuf) != -1) {
				sb.append(cbuf);
			}
		}
		return new Document(this.domain, path, sb.toString());
	}

	private Reader getReader(HttpURLConnection con) throws IOException {
		InputStream is = con.getInputStream();
		final String ce = con.getContentEncoding();
		if (ce != null && ce.indexOf("gzip") > -1) {
			is = new GZIPInputStream(is);
		}
		final String contentType = Optional.ofNullable(con.getContentType()).orElse("").toUpperCase();
		final Charset c = Charset.forName(contentType.indexOf("ISO-8859-1") > -1 ? "ISO-8859-1" : contentType.indexOf("UTF-16") > -1 ? "UTF-16" : "UTF-8");
		return new InputStreamReader(is, c);
	}
}
