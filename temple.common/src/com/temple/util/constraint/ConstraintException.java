package com.temple.util.constraint;

import com.temple.LocaleViewableTempleException;
import com.temple.Module;
import com.temple.view.LocaleViewable;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class ConstraintException extends LocaleViewableTempleException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param cause
	 */
	public ConstraintException(Throwable cause) {
		super(Module.DEFAULT, cause);
	}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param parameters
	 */
	public ConstraintException(Object[] parameters) {
		super(parameters, Module.DEFAULT);
	}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param parameters
	 * @param cause
	 */
	public ConstraintException(Object[] parameters, Throwable cause) {
		super(parameters, Module.DEFAULT, cause);
	}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param lv
	 * @param cause
	 */
	public ConstraintException(LocaleViewable lv, Throwable cause) {
		super(lv, cause);
	}
}
