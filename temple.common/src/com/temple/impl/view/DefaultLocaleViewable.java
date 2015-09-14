package com.temple.impl.view;

import com.temple.view.LocaleBundle;
import com.temple.view.LocaleViewable;
import java.io.Serializable;

/**
 * Default implementation of {@link LocaleViewable}.
 * 
 * @author Florent
 */
public class DefaultLocaleViewable implements LocaleViewable {

	private static final long serialVersionUID = 1L;

	private final String key;

	private Serializable[] parameters;

	private final LocaleBundle bundle;

	// proxyability
	DefaultLocaleViewable() {
		this(null, null, null) ;
	}
	
	public DefaultLocaleViewable(String key, Serializable[] parameters, LocaleBundle module) {
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
	public Serializable[] getLocaleParameters() {
		return this.parameters;
	}

	protected void setParameters(Serializable... parameters) {
		this.parameters = parameters;
	}
	
	@Override
	public LocaleBundle getBundle() {
		return this.bundle;
	}
}
