package com.temple.view;

/**
 * Helper class for {@link LocaleViewable}.
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class LocaleViewableHelper {

	private LocaleViewableHelper() {}

	/**
	 * TODOC
	 * 
	 * @param e an enum
	 * @return
	 */
	public static final String createLocaleKey(Enum<?> e) {
		return e.getClass().getName() + LocaleViewable.DELIMITER + e.name();
	}
	
}
