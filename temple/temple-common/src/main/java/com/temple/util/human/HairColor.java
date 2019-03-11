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
public enum HairColor implements LocaleViewable {
	BLACK,
	BROWN,
	BLOND,
	RED,
	GREY,
	WHITE,
	MIXED,
	NONE_SHAVED;

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
