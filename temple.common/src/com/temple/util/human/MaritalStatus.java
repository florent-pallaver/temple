package com.temple.util.human;

import com.temple.Module;
import com.temple.view.LocaleViewable;
import com.temple.view.LocaleViewableHelper;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public enum MaritalStatus implements LocaleViewable {
	SINGLE,
	IN_A_RELATIONSHIP,
	IN_A_CIVIL_UNION,
	MARRIED,
	IN_AN_OPEN_RELATIONSHIP,
	SEPARATED,
	DIVORCED,
	WIDOWED;

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
		return Module.DEFAULT;
	}
}
