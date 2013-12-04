package com.temple.ejb.session;

import com.temple.ejb.model.EntityException;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public final class SignUpException extends EntityException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param ui
	 * @param cause
	 */
	public SignUpException(String login, Exception cause) {
		super(new Object[]{ login }, cause);
	}
}
