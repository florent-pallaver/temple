package com.temple.service.ejb.action;

import com.temple.LocaleViewableTempleException;
import com.temple.Module;

/**
 * Thrown when an {@link ActionType} is not supported by the application.
 *
 * @author Florent
 */
public final class UnsupportedActionTypeException extends LocaleViewableTempleException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 *
	 * @param parameters
	 * @param module
	 */
	public UnsupportedActionTypeException(Class<?> type) {
		super(new Object[]{ type }, Module.SERVICE);
	}
}
