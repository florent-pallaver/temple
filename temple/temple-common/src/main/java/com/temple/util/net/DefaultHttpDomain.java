package com.temple.util.net;

import java.util.Objects;

public class DefaultHttpDomain implements HttpDomain<DefaultHttpDomain> {

	private static final long serialVersionUID = 1L;

	private final String domainName;

	private final boolean secure;

	/**
	 * @throws IllegalArgumentException if {@link #domainName} is <code>null</code>
	 */
	public DefaultHttpDomain(String domainName) throws IllegalArgumentException {
		this(domainName, false);
	}

	/**
	 * @throws IllegalArgumentException if {@link #domainName} is <code>null</code>
	 */
	public DefaultHttpDomain(String domainName, boolean secure) throws IllegalArgumentException {
		super();
		this.domainName = Objects.requireNonNull(domainName, "domainName cannot be null!");
		this.secure = secure;
	}

	@Override
	public String getName() {
		return this.domainName;
	}

	@Override
	public boolean isSecure() {
		return this.secure;
	}

	@Override
	public int compareTo(DefaultHttpDomain dd) {
		return this.domainName.compareTo(dd.domainName);
	}

	@Override
	public String toString() {
		return (this.secure ? "https://" : "http://") + this.domainName ;
	}

}
