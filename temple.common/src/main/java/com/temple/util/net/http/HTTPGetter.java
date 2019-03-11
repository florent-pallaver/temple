package com.temple.util.net.http;

import java.io.Serializable;

/**
 * @author flominou
 */
public interface HTTPGetter<F extends Serializable> {

	String HTTP_PROTOCOL = "http";

	String HTTPS_PROTOCOL = "https";

	int DEFAULT_CONNECT_TIMEOUT = 90000; // in milliseconds

	/**
	 *
	 * @return the {@link Cookie}
	 */
	Cookie getCookie() ;

	/**
	 *
	 * @param fileURL
	 * @return
	 * @throws HTTPGetException
	 */
	F getFile(String fileURL) throws HTTPGetException ;

	/**
	 * Sets the timeout
	 *
	 * @param timeout the timeout to set
	 */
	void setTimeout(int timeout);

	/**
	 * @return the timeout
	 */
	int getTimeout();

}
