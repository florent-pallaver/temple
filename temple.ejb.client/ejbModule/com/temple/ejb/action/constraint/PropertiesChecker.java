package com.temple.ejb.action.constraint;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Map;

import com.temple.Module;
import com.temple.ejb.action.Action;
import com.temple.ejb.action.Property;
import com.temple.view.LocaleViewable;

/**
 * TODOC
 *
 * @author cr9z1k
 * @version 1.0
 * @param <C>
 */
public abstract class PropertiesChecker<C extends Annotation> implements Checker {

	private final String[] keys;

	private final String suffix;

	/**
	 * Constructor.
	 *
	 * @param keys
	 * @param constraint
	 */
	protected PropertiesChecker(String[] keys, C constraint) {
		this.keys = keys;
		this.suffix = constraint.annotationType().getSimpleName();
	}

	@Override
	public final void check(Action<?> a) throws ConstraintException {
		final Map<String, Property> ps = a.getProperties();
		for (final String k : this.keys) {
			final Property p = ps.get(k);
			if (!this.isValid(p.getValue())) {
				Object[] lps = this.getLocaleParameters();
				if (lps == null) {
					lps = LocaleViewable.NO_PARAMETERS;
				}
				final Object[] flps = new Object[lps.length + 1];
				flps[0] = p;
				System.arraycopy(lps, 0, flps, 1, lps.length);
				throw new ConstraintException(this.suffix, flps, Module.SERVICE);
			}
		}
	}

	/**
	 * @param value - a value to validate.
	 * @return <code>true</code> if the given value is valid, <code>false</code> otherwise.
	 */
	protected abstract boolean isValid(Serializable value);

	/**
	 * TODOC
	 *
	 * @return
	 */
	protected abstract Object[] getLocaleParameters();
}
