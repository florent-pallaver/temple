package com.temple.ejb.session;

/**
 * Thrown whenever a user tries to log in and doesn't provide the right password for that account.
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public final class IncorrectPasswordException extends SignInException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 */
	public IncorrectPasswordException() {
		super();
	}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param cause
	 */
	public IncorrectPasswordException(Throwable cause) {
		super(cause);
	}
}
