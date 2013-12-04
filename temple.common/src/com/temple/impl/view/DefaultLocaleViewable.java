package com.temple.impl.view;

import com.temple.view.LocaleBundle;
import com.temple.view.LocaleViewable;

/**
 * Default implementation of {@link LocaleViewable}.
 * 
 * @author Florent
 */
public class DefaultLocaleViewable implements LocaleViewable {

	private static final long serialVersionUID = 1L;

	private final String key;

	private final Object[] parameters;

	private final LocaleBundle bundle;

	public DefaultLocaleViewable(String key, Object[] parameters, LocaleBundle module) {
		super();
		this.key = key;
		this.parameters = parameters;
		this.bundle = module;
	}

	@Override
	public String getLocaleKey() {
		return this.key;
	}

	@Override
	public Object[] getLocaleParameters() {
		return this.parameters;
	}

	@Override
	public LocaleBundle getBundle() {
		return this.bundle;
	}
}
