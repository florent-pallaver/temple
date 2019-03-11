package com.temple.service.ejb.action.constraint;

import com.temple.service.ejb.action.Action;

public interface Checker {

	/**
	 * TODOC
	 * 
	 * @param a
	 * @throws ConstraintException
	 */
	void check(Action<?> a) throws ConstraintException;
}
