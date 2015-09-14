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
	TRANS_FEMALE, // once a male now a female
	TRANS_MALE, // once a female now a male
	OTHER;

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
