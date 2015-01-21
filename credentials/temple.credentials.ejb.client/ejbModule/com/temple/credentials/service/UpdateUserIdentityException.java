package com.temple.credentials.service;

import com.temple.view.LocaleViewable;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public final class UpdateUserIdentityException extends CredentialsException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param cause
	 */
	public UpdateUserIdentityException(Throwable cause) {
		super(cause, LocaleViewable.NO_PARAMETERS);
	}
}
