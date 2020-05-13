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
	RELATIONSHIP,
	OPEN_RELATIONSHIP,
	CIVIL_UNION,
	OPEN_CIVIL_UNION,
	MARRIED,
	OPEN_MARRIAGE,
	SEPARATED,
	DIVORCED,
	WIDOWED;

	private final String localeKey = LocaleViewableHelper.createLocaleKey(this);

	@Override
	public String getLocaleKey() {
		return this.localeKey;
	}

	@Override
	public Module getBundle() {
		return Module.DEFAULT;
	}
}
