package com.temple;

import com.temple.view.LocaleBundle;
import com.temple.view.LocaleViewable;

/**
 * Base class for <strong>checked</strong> exceptions of this application.
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class LocaleViewableTempleException extends AbstractTempleException implements LocaleViewable {

	private static final long serialVersionUID = 1L;

	private final String key;

	private final Object[] parameters;

	private final LocaleBundle module;

	/**
	 * Constructor.
	 * 
	 * @param module - the module of this exception.
	 * @param cause - the cause of this exception
	 */
	protected LocaleViewableTempleException(Module module, Throwable cause) {
		this(LocaleViewable.NO_PARAMETERS, module, cause);
	}

	/**
	 * Constructor.
	 * 
	 * @param parameters - objects used to complete the views of this exception.
	 * @param module - the module of this exception.
	 */
	protected LocaleViewableTempleException(Object[] parameters, Module module) {
		this(parameters, module, null);
	}

	/**
	 * Constructor.
	 * 
	 * @param parameters - objects used to complete the views of this exception.
	 * @param module - the module of this exception.
	 * @param cause - the cause of this exception
	 */
	protected LocaleViewableTempleException(Object[] parameters, Module module, Throwable cause) {
		this(null, parameters, module, cause);
	}

	/**
	 * Constructor.
	 * 
	 * @param keySuffix - the suffix to add to the locale key.
	 * @param parameters - objects used to complete the views of this exception.
	 * @param module - the module of this exception.
	 */
	protected LocaleViewableTempleException(String keySuffix, Object[] parameters, Module module) {
		this(keySuffix, parameters, module, null);
	}

	/**
	 * Constructor.
	 * 
	 * @param parameters - objects used to complete the views of this exception.
	 * @param module - the module of this exception.
	 * @param keySuffix - the suffix to add to the locale key.
	 * @param cause - the cause of this exception
	 */
	protected LocaleViewableTempleException(String keySuffix, Object[] parameters, Module module, Throwable cause) {
		super(cause);
		if (module == null) {
			throw new IllegalArgumentException("The module parameter cannot be null.");
		}
		String k = module.packageName + LocaleViewable.DELIMITER + this.getClass().getSimpleName();
		if (keySuffix != null) {
			k += LocaleViewable.DELIMITER + keySuffix;
		}
		this.key = k;
		this.parameters = parameters;
		this.module = module;
	}

	/**
	 * Constructor.
	 * 
	 * @param lv -
	 * @param cause - the cause of this exception.
	 */
	protected LocaleViewableTempleException(LocaleViewable lv, Throwable cause) {
		super(cause);
		this.key = lv.getLocaleKey();
		this.parameters = lv.getLocaleParameters();
		this.module = lv.getBundle();
	}

	@Override
	public final String getLocaleKey() {
		return this.key;
	}

	@Override
	public final Object[] getLocaleParameters() {
		return this.parameters;
	}

	@Override
	public final LocaleBundle getBundle() {
		return this.module;
	}
}
