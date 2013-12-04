package com.temple.view;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public interface LocaleBundle {

	/**
	 * The root package of every locale bundle.
	 */
	String BASE_NAME_PREFIX = "resource";

	/**
	 * the default locale file used to resolve a {@link LocaleViewable}.
	 */
	String DEFAULT_LOCALE_FILE_NAME = "locale";

	/**
	 * TODOC
	 * 
	 * @return
	 */
	String getBaseName();
}
