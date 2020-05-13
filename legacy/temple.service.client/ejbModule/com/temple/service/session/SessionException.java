package com.temple.service.session;

import com.temple.service.ServiceException;
import com.temple.view.LocaleViewable;
import java.io.Serializable;

/**
 * Base exception class for session related exception.
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class SessionException extends ServiceException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 */
	protected SessionException() {
		super(LocaleViewable.NO_PARAMETERS);
	}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param parameters
	 */
	public SessionException(Serializable[] parameters) {
		super(parameters);
	}

}
