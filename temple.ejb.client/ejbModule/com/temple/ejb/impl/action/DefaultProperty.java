package com.temple.ejb.impl.action;

import java.io.Serializable;

import com.temple.Module;
import com.temple.ejb.action.Property;
import com.temple.impl.view.DefaultLocaleViewable;

/**
 * Base implementation of a {@link Property}.
 *
 * @author cr9z1k
 * @version 1.0
 */
public class DefaultProperty extends DefaultLocaleViewable implements Property {

	private static final long serialVersionUID = 1L;

	private final Serializable[] value;

	/**
	 * Constructor.
	 *
	 * @param l TODOC
	 * @param key TODOC
	 */
	public DefaultProperty(String key) {
		this(key, new Serializable[1]);
	}

	private DefaultProperty(String key, Serializable[] value) {
		super(key, value, Module.SERVICE);
		if (key == null) {
			throw new IllegalArgumentException("The parameter \"key\" can't be null.");
		}
		this.value = value;
	}

	@Override
	public Serializable getValue() {
		return this.value[0];
	}

	@Override
	public void setValue(Serializable value) {
		this.value[0] = value;
	}
}
