package com.temple.util.net.http;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map.Entry;

import com.temple.util.AbstractLogger;

public final class DefaultHTTPGetter extends AbstractLogger implements HTTPGetter<File>, Serializable {

	private static final long serialVersionUID = 1L;

	private static String USER_AGENT = "Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:22.0) Gecko/20100101 Firefox/22.0";

	private static String ACCEPT = "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8";

	private final File downloadFolder;
	
	private int timeout;

	public DefaultHTTPGetter(File downloadFolder) {
		this.downloadFolder = downloadFolder;
		this.timeout = HTTPGetter.DEFAULT_CONNECT_TIMEOUT;
	}

	@Override
	public Cookie getCookie() {
		return null;
	}

	@Override
	public int getTimeout() {
		return this.timeout;
	}

	@Override
	public File getFile(String httpUrl) throws HTTPGetException {
		HttpURLConnection con = null;
		try {
			con = this.getConnection(httpUrl);
			con.connect();
			this.checkConnection(con);
			return HTTPGetter.downloadFile(con, this.downloadFolder);
		} catch (final IOException e) {
			if (this.isDebugLoggable()) {
				for (final Entry<String, List<String>> hf : con.getHeaderFields().entrySet()) {
					this.debug(hf.getKey() + hf.getValue());
				}
			}
			throw new HTTPGetException(con, e);
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
	}

	private HttpURLConnection getConnection(String httpUrl) throws IOException {
		final URL url = new URL(httpUrl);
		final URLConnection con = url.openConnection();
		con.setRequestProperty("User-Agent", DefaultHTTPGetter.USER_AGENT);
		con.setRequestProperty("Connection", "keep-alive");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("Accept-Encoding", "gzip,deflate");
		con.setRequestProperty("Accept", DefaultHTTPGetter.ACCEPT);
		con.setDefaultUseCaches(false);
		con.setConnectTimeout(this.timeout);
		con.setReadTimeout(this.timeout);
		return (HttpURLConnection) con;
	}

	private void checkConnection(HttpURLConnection con) throws HTTPGetException, IOException {
		final int statusCode = con.getResponseCode();
		switch (statusCode) {
		case 200: // OK
			break;
		case 301:
		case 302:
			final String location = con.getHeaderField("Location");
			this.warning(statusCode + " - " + location);
			throw new HTTPGetException(con, statusCode);
		case 407:
			for (final Entry<String, List<String>> e : con.getHeaderFields().entrySet()) {
				this.info(e.getKey() + e.getValue());
			}
		default:
			this.warning("Unable to get document: " + statusCode);
			throw new HTTPGetException(con, statusCode);
		}
	}
}
