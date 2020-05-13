package com.temple.util.net.http;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.temple.Module;
import com.temple.util.net.Domain;

/**
 * @author florent
 */
public class Cookie implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final DateFormat dateFormat = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss zzz");

	private Domain<?> domain;

	// TODO add length
	private String content;

	// TODO add length
	private String path;

	private Timestamp expiry;

	private transient Map<String, String> values;

	private boolean httpOnly;

	protected Cookie() {}

	/**
	 * @param domain
	 * @param content
	 * @param proxy
	 */
	public Cookie(Domain<?> domain) {
		super();
		this.domain = domain;
		this.content = "";
	}

	public Domain<?> getDomain() {
		return this.domain;
	}

	public String getContent() {
		return this.content;
	}

	public String getPath() {
		return this.path;
	}

	public Timestamp getExpiry() {
		return this.expiry;
	}

	public String getValue(String key) {
		return this.values.get(key);
	}

	public Map<String, String> getValues() {
		return this.values;
	}

	public void setValues(Map<String, String> values) {
		this.values = values;
	}

	public boolean isHttpOnly() {
		return this.httpOnly;
	}

	public void setHttpOnly(boolean httpOnly) {
		this.httpOnly = httpOnly;
	}

	public void setValues(String values) {
		// Module.MODEL.logger.warning("Setting cookie[" + this.proxyId + ", " + this.domain.ordinal() + "] [" + values
		// + "] above [" + this.content + "]");
		if (this.values == null) {
			this.values = new HashMap<>();
			if (this.content.length() > 0) {
				for (final String part : values.split("; ")) {
					final String[] split = part.split("=", 2);
					this.values.put(split[0], split[1]);
				}
			}
		}
		final StringBuilder domain = new StringBuilder();
		String path = "";
		for (final String part : values.split("; ")) {
			if (part.toLowerCase().startsWith("expires=")) {
				try {
					this.expiry = new Timestamp(Cookie.dateFormat.parse(part.substring(8)).getTime());
				} catch (final ParseException e) {
					Module.DEFAULT.logger.severe(e.getLocalizedMessage());
				}
			} else if (part.toLowerCase().startsWith("path=")) {
				path = part.substring(5);
			} else if (part.toLowerCase().startsWith("domain=")) {
				domain.append(part.substring(7));
			} else {
				final String[] split = part.split("=", 2);
				if (split.length == 1) {
					if (part.toLowerCase().equals("httponly")) {
						this.httpOnly = true;
					} else if (part.toLowerCase().equals("secure")) {
						Module.DEFAULT.logger.fine("secure cookie ...");
					} else {
						Module.DEFAULT.logger.warning(part);
						Module.DEFAULT.logger.warning(values);
					}
				} else {
					this.values.put(split[0], split[1]);
				}
			}
		}
		this.path = domain.append(path).toString();
		int i = this.values.size();
		final StringBuilder sb = new StringBuilder();
		for (final Entry<String, String> e : this.values.entrySet()) {
			sb.append(e.getKey()).append('=').append(e.getValue());
			if (--i > 0) {
				sb.append("; ");
			}
		}
		this.content = sb.toString();
	}

}
