package com.temple.util.net;

import java.io.Serializable;

import com.temple.util.Nameable;

public interface Domain<D extends Domain<D>> extends Serializable, Comparable<D>, Nameable {

	/**
	 * @return the Internet domain associated to this object, never <code>null</code>.
	 */
	@Override
	String getName();

	/**
	 * @return <code>true</code> if HTTP connection to this domain should be made through an HTTPS connection,
	 *         <code>false</code> otherwise.
	 */
	boolean useHttps();

}
