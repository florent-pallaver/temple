package com.temple.ejb.session;

import com.temple.model.User;
import com.temple.model.access.AccessType;

/**
 * Exception thrown whenever an {@link User} tries to perform an action on an Object and is not allowed to.
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public final class AccessDeniedException extends SessionException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 */
	public AccessDeniedException() {
		super();
	}

	/**
	 * Constructor.
	 *
	 * @param p - the access type that is not allowed.
	 */
	public AccessDeniedException(AccessType p) {
		super(new Object[]{ p });
	}
}
