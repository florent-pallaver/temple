package com.temple.credentials.ejb.model;

import com.temple.view.LocaleViewable;

/**
 * Thrown whenever an incorrect pass has been given.
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public final class IncorrectPassException extends CredentialsException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 */
	public IncorrectPassException() {
		super(LocaleViewable.NO_PARAMETERS);
	}
}
