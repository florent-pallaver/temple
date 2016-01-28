package com.temple.util.net.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.util.zip.GZIPInputStream;

import com.temple.util.TempleUtil;
import com.temple.util.net.Document;
import com.temple.util.net.Domain;

/**
 * Utility class to get document through HTTP or HTTPS protocol.
 * To be consistent, the same object should be used to get documents for and only for one given session as the referrer
 * and cookie are automatically refreshed.
 * @author flominou
 */
public final class HTTPDocumentGetter extends AbstractHTTPGetter<Document> implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int BUFFER_SIZE = 8192; // in bytes

	/**
	 * Constructor
	 *
	 * @param domain
	 */
	public HTTPDocumentGetter(Domain<?> domain) {
		super(domain) ;
	}

	@Override
	protected Document getFile0(HttpURLConnection con, String path) throws HTTPGetException {
		final StringBuilder sb = new StringBuilder();
		final char[] cbuf = new char[HTTPDocumentGetter.BUFFER_SIZE];
		Reader reader = null ;
		try {
			InputStream is = con.getInputStream();
			final String ce = con.getContentEncoding();
			if (ce != null && ce.indexOf("gzip") > -1) {
				is = new GZIPInputStream(is);
			}
			final String contentType = con.getContentType().toUpperCase();
			final Charset c = Charset.forName(contentType.indexOf("ISO-8859-1") > -1 ? "ISO-8859-1" : contentType.indexOf("UTF-16") > -1 ? "UTF-16" : "UTF-8");
			reader = new InputStreamReader(is, c);
			while (reader.read(cbuf) != -1) {
				sb.append(cbuf);
			}
		} catch (final IOException e) {
			throw new HTTPGetException(e);
		} finally {
			TempleUtil.close(reader);
		}
		return new Document(this.domain, path, sb.toString()) ;
	}
}
