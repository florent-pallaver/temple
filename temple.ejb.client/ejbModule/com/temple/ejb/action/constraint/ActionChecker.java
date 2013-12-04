package com.temple.ejb.action.constraint;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import com.temple.ejb.action.Action;

/**
 * TODOC
 * 
 * @author cr9z1k
 * @version 1.0
 */
public final class ActionChecker implements Checker {

	private final Collection<String> keys;

	private final PropertiesChecker<?>[] checkers;

	/**
	 * Constructor.
	 * 
	 * @param keys
	 * @param checkers
	 */
	public ActionChecker(String[] keys, PropertiesChecker<?>[] checkers) {
		super();
		this.keys = Arrays.asList(keys);
		this.checkers = checkers;
	}

	@Override
	public final void check(Action<?> a) throws ConstraintException {
		if (a == null) {
			throw new IllegalArgumentException("null Action given."); // FIXME use ConstraintException
		}
		final Set<String> apk = a.getProperties().keySet();
		if (!apk.containsAll(this.keys)) {
			throw new IllegalArgumentException("Some properties of the action aren't present."); // FIXME use ConstraintException
		}
		for (PropertiesChecker<?> c : this.checkers) {
			c.check(a);
		}
	}
}
