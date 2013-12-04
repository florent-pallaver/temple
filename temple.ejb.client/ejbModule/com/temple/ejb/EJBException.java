package com.temple.ejb;

import com.temple.LocaleViewableTempleException;
import com.temple.Module;
import com.temple.view.LocaleViewable;

/**
 * Base class for EJB exceptions of this application.
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class EJBException extends LocaleViewableTempleException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * 
	 * @param cause - the cause of this exception
	 */
	protected EJBException(Throwable cause) {
		this(LocaleViewable.NO_PARAMETER, cause);
	}

	/**
	 * Constructor.
	 * 
	 * @param parameters - objects used to complete the views of this exception.
	 */
	protected EJBException(Object[] parameters) {
		this(parameters, null);
	}

	/**
	 * Constructor.
	 * 
	 * @param parameters - objects used to complete the views of this exception.
	 * @param cause - the cause of this exception
	 */
	protected EJBException(Object[] parameters, Throwable cause) {
		super(parameters, Module.EJB, cause);
	}
}
