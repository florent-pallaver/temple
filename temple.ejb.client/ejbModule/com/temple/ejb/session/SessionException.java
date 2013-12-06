package com.temple.ejb.session;

import com.temple.ejb.EJBException;
import com.temple.view.LocaleViewable;

/**
 * Base exception class for session related exception.
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class SessionException extends EJBException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 */
	protected SessionException() {
		super(LocaleViewable.NO_PARAMETER);
	}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param cause
	 */
	public SessionException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param parameters
	 */
	public SessionException(Object[] parameters) {
		super(parameters);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param parameters
	 * @param cause
	 */
	public SessionException(Object[] parameters, Throwable cause) {
		super(parameters, cause);
		// TODO Auto-generated constructor stub
	}
}