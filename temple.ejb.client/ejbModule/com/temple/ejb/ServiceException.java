package com.temple.ejb;

import com.temple.LocaleViewableTempleException;
import com.temple.Module;
import com.temple.view.LocaleBundle;
import com.temple.view.LocaleView;
import com.temple.view.LocaleViewable;

/**
 * Base class for exceptions of this application's services.
 *
 * @author Florent Pallaver
 * @version 1.0
 */
// FIXME change package etc
public abstract class ServiceException extends LocaleViewableTempleException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 *
	 * @param cause - the cause of this exception
	 */
	protected ServiceException(Throwable cause) {
		this(LocaleViewable.NO_PARAMETERS, cause);
	}

	/**
	 * Constructor.
	 *
	 * @param parameters - objects used to complete the views of this exception.
	 */
	protected ServiceException(Object[] parameters) {
		this(parameters, null);
	}

	/**
	 * Constructor.
	 *
	 * @param parameters - objects used to complete the views of this exception.
	 * @param cause - the cause of this exception
	 */
	protected ServiceException(Object[] parameters, Throwable cause) {
		this(Module.SERVICE, parameters, cause);
	}

	/**
	 * Constructor.
	 *
	 * @param bundle - the {@link LocaleBundle} to use to create a {@link LocaleView} out of this exception
	 * @param cause - the cause of this exception
	 */
	protected ServiceException(LocaleBundle bundle, Throwable cause) {
		this(bundle, LocaleViewable.NO_PARAMETERS, cause);
	}

	/**
	 * Constructor.
	 *
	 * @param bundle - the {@link LocaleBundle} to use to create a {@link LocaleView} out of this exception
	 * @param parameters - objects used to complete the views of this exception.
	 */
	protected ServiceException(LocaleBundle bundle, Object[] parameters) {
		this(bundle, parameters, null);
	}

	/**
	 * Constructor.
	 *
	 * @param bundle - the {@link LocaleBundle} to use to create a {@link LocaleView} out of this exception
	 * @param parameters - objects used to complete the views of this exception.
	 * @param cause - the cause of this exception
	 */
	protected ServiceException(LocaleBundle bundle, Object[] parameters, Throwable cause) {
		super(parameters, bundle, cause);
	}
}
