package com.temple.service.ejb.action.constraint;

import java.io.Serializable;
import java.util.Arrays;

import com.temple.util.TempleUtil;

/**
 * TODOC
 * 
 * @author cr9z1k
 * @version 1.0
 */
public final class ForbiddenStringsConstraintChecker extends PropertiesChecker<ForbiddenStringsConstraint> {

	private final String[] forbidden;

	private transient Object[] parameters;

	/**
	 * Constructor.
	 * 
	 * @param keys
	 * @param constraint
	 */
	public ForbiddenStringsConstraintChecker(String[] keys, ForbiddenStringsConstraint constraint) {
		super(keys, constraint);
		this.forbidden = constraint.forbidden();
	}

	@Override
	protected boolean isValid(Serializable value) {
		return value instanceof String && !TempleUtil.contains(this.forbidden, value);
	}

	@Override
	protected Object[] getLocaleParameters() {
		if (this.parameters == null) {
			this.parameters = new Object[] { Arrays.toString(this.forbidden) };
		}
		return this.parameters;
	}
}
