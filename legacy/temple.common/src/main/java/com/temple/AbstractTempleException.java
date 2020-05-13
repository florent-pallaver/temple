package com.temple;


/**
 * Base class for <strong>checked</strong> exceptions of this application.
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
abstract class AbstractTempleException extends Exception {

	private static final long serialVersionUID = 1L;

	protected AbstractTempleException() {
		this(null, null);
	}

	protected AbstractTempleException(String message) {
		this(message, null);
	}

	protected AbstractTempleException(Throwable cause) {
		this(null, cause);
	}

	protected AbstractTempleException(String message, Throwable cause) {
		super(message, cause);
	}
}
