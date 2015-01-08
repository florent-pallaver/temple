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
public enum Gender implements LocaleViewable {
	FEMALE,
	MALE,
	TRANS_FEMALE,
	TRANS_MALE,
	OTHER;

	private final String localeKey = LocaleViewableHelper.createLocaleKey(this);

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
		return Module.DEFAULT;
	}
}
