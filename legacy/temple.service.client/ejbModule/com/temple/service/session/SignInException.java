package com.temple.service.session;

import com.temple.service.ServiceException;
import com.temple.view.LocaleViewable;
import java.io.Serializable;

/**
 * Base class for exceptions occuring while a user is trying to sign in.
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class SignInException extends ServiceException {

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
	protected SignInException(Throwable cause, Serializable... parameters) {
		super(parameters, cause);
	}
}
