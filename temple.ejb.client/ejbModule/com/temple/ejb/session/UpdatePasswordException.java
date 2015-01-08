package com.temple.ejb.session;

import com.temple.ejb.model.EntityException;
import com.temple.view.LocaleViewable;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public final class UpdatePasswordException extends EntityException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * 
	 * @param e - the cause of this exception
	 */
	public UpdatePasswordException(Exception e) {
		super(LocaleViewable.NO_PARAMETERS, e);
	}
}
