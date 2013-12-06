package com.temple.cdi.session;

import com.temple.util.human.Language;
import com.temple.view.LocaleViewable;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public interface LanguageBean {

	/**
	 * @return the {@link Language} currently in use.
	 */
	Language getCurrentLanguage();

	/**
	 * Sets the current {@link Language}
	 * 
	 * @param language the {@link Language} to set
	 */
	void setCurrentLanguage(Language language);

	/**
	 * TODOC
	 * 
	 * @param lv
	 * @return
	 */
	String getString(LocaleViewable lv);

	/**
	 * TODOC
	 * 
	 * @param key
	 * @param objects
	 * @return
	 */
	String getString(String key, Object... objects);

	/**
	 * TODOC
	 * 
	 * @param lv
	 * @return
	 */
	String getDetailedString(LocaleViewable lv);

	/**
	 * TODOC
	 * 
	 * @param key
	 * @param objects
	 * @return
	 */
	String getDetailedString(String key, Object... objects);
}