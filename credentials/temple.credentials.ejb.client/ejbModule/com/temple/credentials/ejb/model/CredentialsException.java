package com.temple.credentials.ejb.model;

import com.temple.LocaleViewableTempleException;
import com.temple.Module;

/**
 * Base class for credentials exceptions.
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class CredentialsException extends LocaleViewableTempleException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param parameters
	 */
	public CredentialsException(Object... parameters) {
		super(parameters, Module.EJB);
	}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param parameters
	 * @param cause
	 */
	public CredentialsException(Throwable cause, Object... parameters) {
		super(parameters, Module.EJB, cause);
	}
}
