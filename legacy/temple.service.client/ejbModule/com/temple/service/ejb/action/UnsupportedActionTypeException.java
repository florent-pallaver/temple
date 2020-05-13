package com.temple.service.ejb.action;

import com.temple.LocaleViewableTempleException;
import com.temple.Module;
import java.io.Serializable;

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
	 * @param type TODOC
	 */
	public UnsupportedActionTypeException(Class<?> type) {
		super(new Serializable[]{ type }, Module.SERVICE);
	}
}
