package com.temple.util;

import java.nio.charset.Charset;

/**
 * Class gathering common and handy {@link Charset charsets}.
 * @author flominou
 * @version 1.0
 */
public abstract class Charsets {

	/**
	 * UTF-8 character set
	 */
	public static final Charset utf8 = Charset.forName("UTF-8");

	private Charsets() {}
	
}
