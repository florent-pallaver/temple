package com.temple.credentials.ejb.model;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public final class LoginNotFoundException extends CredentialsException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * 
	 * @param login - the not found login.
	 */
	public LoginNotFoundException(String login) {
		super(login);
	}
}
