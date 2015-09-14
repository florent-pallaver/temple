package com.temple.view;

import java.io.Serializable;

/**
 * A LocaleViewable is a {@link Viewable} which requires a Locale to be viewed.
 *
 * @author Florent Pallaver
 * @version 1.0
 * @see Viewable
 * @see LocaleView
 */
public interface LocaleViewable extends Viewable {

	/**
	 * The char to use to delimit parts of a {@link #getLocaleKey() key}.
	 */
	char DELIMITER = '.';

	/**
	 * The constant to use when no parameter is needed in a LocaleViewable.
	 */
	Serializable[] NO_PARAMETERS = {};

	/**
	 * @return a key to find a localized string, never <code>null</code>.
	 */
	String getLocaleKey();

	/**
	 * Gives an array of objects to complete the localized string.
	 * <p>
	 * If any LocaleViewable is in this array, then it will be transformed to a localized string to complete this
	 * LocaleViewable.
	 *
	 * @return the parameters to complete the localized string.
	 * @see #NO_PARAMETERS
	 */
	default Serializable[] getLocaleParameters() {
		return LocaleViewable.NO_PARAMETERS;
	}

	/**
	 * @return the {@link LocaleBundle} to use to transform this LocaleViewable.
	 */
	LocaleBundle getBundle();
}
