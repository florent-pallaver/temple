package com.temple.ejb.action.constraint;

import java.io.Serializable;


/**
 * TODOC 
 * @author cr9z1k
 * @version 1.0
 */
public final class RangeConstraintChecker extends PropertiesChecker<RangeConstraint> {

	private final Integer[] range	= new Integer[2] ;
	
	/**
	 * Constructor.
	 * @param keys
	 * @param constraint
	 */
	public RangeConstraintChecker(String[] keys, RangeConstraint constraint) {
		super(keys, constraint);
		this.range[0] = constraint.min() ;
		this.range[1] = constraint.max() ;
	}

	@Override
	protected boolean isValid(Serializable s) {
		final boolean b;
		if(s instanceof Integer) {
			final int i = (Integer)s;
			b = this.range[0] <= i && i <= this.range[1] ;
		} else {
			b = false;
		}
		return b;
	}

	@Override
	protected Object[] getLocaleParameters() {
		return this.range ;
	}

}
