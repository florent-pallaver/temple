package com.temple.ejb.action.constraint;

import com.temple.ejb.action.Action;

public interface Checker {

	/**
	 * TODOC
	 * 
	 * @param a
	 * @throws ConstraintException
	 */
	void check(Action<?> a) throws ConstraintException;
}
