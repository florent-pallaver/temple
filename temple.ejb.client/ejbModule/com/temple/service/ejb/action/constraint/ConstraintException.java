package com.temple.service.ejb.action.constraint;

import com.temple.LocaleViewableTempleException;
import com.temple.Module;
import com.temple.util.Valuable;

/**
 * Exception thrown by {@link Validator}s.
 * 
 * @author cr9z1k
 * @version 1.0
 * @see Validator#validate(Valuable)
 */
public final class ConstraintException extends LocaleViewableTempleException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * 
	 * @param keySuffix - the suffix to add to the locale key.
	 * @param parameters - objects used to complete the views of this exception.
	 * @param module - the module of this exception.
	 */
	public ConstraintException(String keySuffix, Object[] parameters, Module module) {
		this(keySuffix, parameters, module, null);
	}

	/**
	 * Constructor.
	 * 
	 * @param keySuffix - the suffix to add to the locale key.
	 * @param parameters - objects used to complete the views of this exception.
	 * @param module - the module of this exception.
	 * @param cause - the cause of this exception
	 */
	public ConstraintException(String keySuffix, Object[] parameters, Module module, Throwable cause) {
		super(keySuffix, parameters, module, cause);
	}
}
