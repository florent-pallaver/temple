package com.temple.credentials.service;


/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public final class CreateUserIdentityException extends CredentialsException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param login
	 * @param cause
	 */
	public CreateUserIdentityException(String login, Throwable cause) {
		super(cause, login);
	}
}
