package com.temple.ejb.session;

/**
 * Exception thrown when no user is attached to the current session.
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public class NoUserSessionException extends SessionException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 */
	public NoUserSessionException() {}
}
