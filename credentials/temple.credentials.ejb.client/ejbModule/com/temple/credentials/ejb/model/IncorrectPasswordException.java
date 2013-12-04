package com.temple.credentials.ejb.model;

import com.temple.view.LocaleViewable;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public final class IncorrectPasswordException extends CredentialsException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 */
	public IncorrectPasswordException() {
		super(LocaleViewable.NO_PARAMETER);
	}
}
