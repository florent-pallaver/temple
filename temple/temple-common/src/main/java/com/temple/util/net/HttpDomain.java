package com.temple.util.net;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

import com.temple.util.Nameable;

public interface HttpDomain<D extends HttpDomain<D>> extends Serializable, Comparable<D>, Nameable {

	/**
	 * @return the Internet domain associated to this object, never <code>null</code>.
	 */
	@Override
	String getName();

	/**
	 * @return <code>true</code> if it is accessible through a secure connection only, <code>false</code> otherwise
	 */
	boolean isSecure();
	
	default URL toURL() {
		try {
			return new URL(this.isSecure() ? "https" : "http", this.getName(), "");
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
}
