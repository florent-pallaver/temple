package com.temple.util.human;

import java.util.Locale;

import com.temple.util.Enumerable;
import com.temple.util.Nameable;
import com.temple.view.LocaleStringView;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public interface Language extends LocaleStringView, Nameable, Enumerable {

	/**
	 * @return the {@link Locale} associated to this {@link Language}.
	 */
	public abstract Locale getLocale();
}
