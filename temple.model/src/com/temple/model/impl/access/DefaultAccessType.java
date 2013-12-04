package com.temple.model.impl.access;

import com.sun.faces.application.resource.Resource;
import com.temple.Module;
import com.temple.model.access.AccessType;
import com.temple.view.LocaleViewable;
import com.temple.view.LocaleViewableHelper;

/**
 * Enumeration of all access types.
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public enum DefaultAccessType implements AccessType {
	/**
	 * Any {@link Action} that consists in modifying the content of a {@link Resource}.
	 */
	WRITE,
	/**
	 * Any {@link Action} that consists in consulting the content of a {@link Resource}.
	 */
	READ;

	private final String localeKey = LocaleViewableHelper.createLocaleKey(this);

	@Override
	public String getLocaleKey() {
		return this.localeKey;
	}

	@Override
	public Object[] getLocaleParameters() {
		return LocaleViewable.NO_PARAMETER;
	}

	@Override
	public Module getBundle() {
		return Module.MODEL;
	}

	@Override
	public DefaultAccessType toEnum() {
		return this;
	}
}
