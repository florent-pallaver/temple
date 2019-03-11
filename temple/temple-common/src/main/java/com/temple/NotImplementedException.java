package com.temple;

/**
 * Convenience exception for unimplemented methods.<br>
 * Thrown whenever a method has not been implemented.
 *
 * @author flominou
 * @version 1.0
 */
public final class NotImplementedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 */
	public NotImplementedException() {
		this(null) ;
	}

	/**
	 * Constructor.
	 *
	 * @param message a message
	 */
	public NotImplementedException(String message) {
		super(message);
	}

}
