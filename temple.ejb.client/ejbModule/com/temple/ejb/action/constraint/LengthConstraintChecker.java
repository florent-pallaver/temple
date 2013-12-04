package com.temple.ejb.action.constraint;

import java.io.Serializable;


/**
 * TODOC 
 * @author cr9z1k
 * @version 1.0
 */
public final class LengthConstraintChecker extends PropertiesChecker<LengthConstraint> {

	private final Integer[] limits = new Integer[2] ;
	
	/**
	 * Constructor.
	 * @param keys
	 * @param constraint
	 */
	public LengthConstraintChecker(String[] keys, LengthConstraint constraint) {
		super(keys, constraint);
		
		this.limits[0] = constraint.min() ;
		this.limits[1] = constraint.max() ;
	}

	@Override
	protected boolean isValid(Serializable s) {
		final boolean b;
		if(s instanceof String) {
			final int l = ((String)s).length();
			b = this.limits[0] <= l && l <= this.limits[1];
		} else {
			b = false;
		}
		return b;
	}

	@Override
	protected Object[] getLocaleParameters() {
		return this.limits ;
	}

}
