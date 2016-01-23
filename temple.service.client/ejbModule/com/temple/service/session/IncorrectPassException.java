package com.temple.service.session;

/**
 * Thrown whenever a user tries to log in and doesn't provide the right password for that account.
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public final class IncorrectPassException extends SignInException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 */
	public IncorrectPassException() {
		super();
	}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param cause
	 */
	public IncorrectPassException(Throwable cause) {
		super(cause);
	}
}
