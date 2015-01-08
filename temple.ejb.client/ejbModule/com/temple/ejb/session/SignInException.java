package com.temple.ejb.session;

import com.temple.ejb.EJBException;
import com.temple.view.LocaleViewable;

/**
 * Base class for exceptions occuring while a user is trying to sign in.
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class SignInException extends EJBException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 */
	protected SignInException() {
		super(LocaleViewable.NO_PARAMETERS);
	}

	/**
	 * Constructor
	 * 
	 * @param cause - the cause of this exception.
	 * @param parameters - the parameters to complete the localized string of this exception.
	 */
	protected SignInException(Throwable cause, Object... parameters) {
		super(parameters, cause);
	}
}
