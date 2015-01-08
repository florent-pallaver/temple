package com.temple.ejb.session;

/**
 * Thrown when an user can't be found.
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public final class UserNotFoundException extends SignInException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 *
	 * @param login - the login not found.
	 */
	public UserNotFoundException(String login) {
		this(login, null);
	}

	/**
	 * Constructor
	 *
	 * @param login - the login not found.
	 * @param cause - the reason why the user hasn't been found.
	 */
	public UserNotFoundException(String login, Throwable cause) {
		super(cause, login);
	}
}
