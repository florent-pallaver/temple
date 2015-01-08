package com.temple.ejb.action.constraint;

import java.io.Serializable;

import com.temple.view.LocaleViewable;

/**
 * TODOC
 * 
 * @author cr9z1k
 * @version 1.0
 */
public final class NotNullConstraintChecker extends PropertiesChecker<NotNullConstraint> {

	/**
	 * Constructor.
	 * 
	 * @param keys
	 * @param constraint
	 */
	public NotNullConstraintChecker(String[] keys, NotNullConstraint constraint) {
		super(keys, constraint);
	}

	@Override
	protected boolean isValid(Serializable s) {
		return s != null;
	}

	@Override
	protected Object[] getLocaleParameters() {
		return LocaleViewable.NO_PARAMETERS;
	}
}
