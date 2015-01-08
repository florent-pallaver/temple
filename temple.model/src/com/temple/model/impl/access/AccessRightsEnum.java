package com.temple.model.impl.access;

import com.temple.Module;
import com.temple.model.access.AccessType;
import com.temple.model.access.Realm;
import com.temple.view.LocaleViewable;
import com.temple.view.LocaleViewableHelper;

/**
 * Enumeration of possible {@link AccessType} combination for any {@link Realm}.
 * 
 * @author Florent Pallaver
 */
public enum AccessRightsEnum implements LocaleViewable {
	/**
	 * TODOC
	 */
	NONE,
	/**
	 * TODOC
	 */
	R_(DefaultAccessType.READ),
	/**
	 * TODOC
	 */
	_W(DefaultAccessType.WRITE),
	/**
	 * TODOC
	 */
	RW(DefaultAccessType.READ, DefaultAccessType.WRITE);

	private final String localeKey;

	private final DefaultAccessType[] accessTypes;

	private AccessRightsEnum(DefaultAccessType... accessEnums) {
		this.localeKey = LocaleViewableHelper.createLocaleKey(this);
		this.accessTypes = accessEnums;
	}

	/**
	 * @return the {@link AccessType}s
	 */
	public DefaultAccessType[] getAccessTypes() {
		return this.accessTypes;
	}

	@Override
	public String getLocaleKey() {
		return this.localeKey;
	}

	@Override
	public Object[] getLocaleParameters() {
		return LocaleViewable.NO_PARAMETERS;
	}

	@Override
	public Module getBundle() {
		return Module.MODEL;
	}
}
