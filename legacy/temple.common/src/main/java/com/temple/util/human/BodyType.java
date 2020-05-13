package com.temple.util.human;

import com.temple.Module;
import com.temple.view.LocaleViewable;
import com.temple.view.LocaleViewableHelper;

/**
 *
 * @author flominou
 */
public enum BodyType implements LocaleViewable {
	
	SLIM,
	AVERAGE,
	// CURVY ?
	TONED,
	ATHLETIC,
	MUSCULAR,
	STOCKY,
	LARGE ;
	
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
