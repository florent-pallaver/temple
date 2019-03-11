/**
 *
 */
package com.temple.util.net;

import java.util.Objects;

/**
 * @author florent
 */
public class DefaultDomain implements Domain<DefaultDomain> {

	private static final long serialVersionUID = 1L;

	private final String domainName;

	private final boolean useHttps;

	/**
	 * @param domainName
	 * @throws IllegalArgumentException if {@link #domainName} is <code>null</code>
	 */
	public DefaultDomain(String domainName) throws IllegalArgumentException {
		this(domainName, false);
	}

	/**
	 * @param domainName
	 * @param useHttps
	 * @throws IllegalArgumentException if {@link #domainName} is <code>null</code>
	 */
	public DefaultDomain(String domainName, boolean useHttps) throws IllegalArgumentException {
		super();
		this.domainName = Objects.requireNonNull(domainName, "domainName cannot be null!");
		this.useHttps = useHttps;
	}

	@Override
	public String getName() {
		return this.domainName;
	}

	@Override
	public boolean useHttps() {
		return this.useHttps;
	}

	@Override
	public int compareTo(DefaultDomain dd) {
		return this.domainName.compareTo(dd.domainName);
	}

	@Override
	public String toString() {
		return (this.useHttps ? "https://" : "http://") + this.domainName ;
	}

}
