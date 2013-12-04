package com.temple.view;

/**
 * Base contract to view a {@link LocaleViewable} with localized Strings.
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public interface LocaleStringView extends LocaleView {

	/**
	 * The suffix to use on a {@link LocaleViewable#getLocaleKey() locale key} to find a more detailed localized String.
	 */
	String DETAIL_KEY_SUFFIX = "_detail";

	/**
	 * @return the localized String of this view.
	 */
	String getString();

	/**
	 * @return the detailed localized String of this view.
	 */
	String getDetailedString();
}
