package com.temple.util.net.http;

import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map.Entry;

import com.temple.Module;
import com.temple.util.AbstractLogger;
import com.temple.util.net.Domain;

public abstract class AbstractHTTPGetter<F extends Serializable> extends AbstractLogger implements HTTPGetter<F>, Serializable {

	private static final long serialVersionUID = 1L;

	public static String USER_AGENT = "Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:22.0) Gecko/20100101 Firefox/22.0";

	public static String ACCEPT = "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8";

	protected String userAgent = AbstractHTTPGetter.USER_AGENT;

	protected final Domain<?> domain;

	protected final String protocol;

	protected final Cookie cookie;

	protected final Proxy proxy;

	private String referrerPath;

	private int timeout;

	protected AbstractHTTPGetter(Domain<?> domain) {
		this(domain, new Cookie(domain));
	}

	protected AbstractHTTPGetter(Domain<?> domain, Cookie cookie) {
		this(domain, cookie, Proxy.NO_PROXY);
	}

	protected AbstractHTTPGetter(Domain<?> domain, Cookie cookie, Proxy proxy) {
		this.domain = domain;
		this.protocol = this.domain.useHttps() ? HTTPGetter.HTTPS_PROTOCOL : HTTPGetter.HTTP_PROTOCOL;
		this.cookie = cookie;
		this.referrerPath = null;
		this.proxy = proxy == null ? Proxy.NO_PROXY : proxy;
		this.timeout = HTTPGetter.DEFAULT_CONNECT_TIMEOUT;
	}

	public final String getUserAgent() {
		return this.userAgent;
	}

	public final void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	@Override
	public Cookie getCookie() {
		return this.cookie;
	}

	@Override
	public int getTimeout() {
		return this.timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	@Override
	public F getFile(String httpUrl) throws HTTPGetException {
		final String path = this.getPath(httpUrl);
		HttpURLConnection con = null;
		try {
			con = this.getConnection(path);
			con.connect();
			this.checkConnection(con);
			final String setCookie = con.getHeaderField("Set-Cookie");
			if (setCookie != null && this.cookie != null) {
				this.cookie.setValues(setCookie);
			}
			final F file = this.getFile0(con, path);
			this.referrerPath = path;
			return file;
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

	protected abstract F getFile0(HttpURLConnection con, String path) throws IOException;

	private String getPath(String httpUrl) {
		final String path;
		if (!httpUrl.startsWith("/")) {
			if (httpUrl.startsWith("http")) {
				path = httpUrl.replace("http://", "").replace("https://", "").replace(this.domain.getName(), "");
			} else {
				path = "/" + httpUrl;
			}
		} else {
			path = httpUrl;
		}
		return path;
	}

	private HttpURLConnection getConnection(String documentPath) throws IOException {
		final URL url = new URL(this.protocol, this.domain.getName(), documentPath);
		final URLConnection con = url.openConnection(this.proxy);
		con.setRequestProperty("User-Agent", AbstractHTTPGetter.USER_AGENT);
		if (this.referrerPath != null) {
			con.setRequestProperty("Referer", this.domain + this.referrerPath);
		}
		con.setRequestProperty("Host", this.domain.getName());
		con.setRequestProperty("Connection", "keep-alive");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("Accept-Encoding", "gzip,deflate");
		con.setRequestProperty("Accept", AbstractHTTPGetter.ACCEPT);
		if (this.cookie != null && this.cookie.getContent().length() > 0) {
			con.setRequestProperty("Cookie", this.cookie.getContent());
		}
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
			Module.DEFAULT.logger.warning(statusCode + " - " + location);
			throw new HTTPGetException(con, statusCode);
		case 407:
			for (final Entry<String, List<String>> e : con.getHeaderFields().entrySet()) {
				Module.DEFAULT.logger.info(e.getKey() + e.getValue());
			}
		default:
			Module.DEFAULT.logger.warning("Unable to get document: " + statusCode);
			throw new HTTPGetException(con, statusCode);
		}
	}

}
