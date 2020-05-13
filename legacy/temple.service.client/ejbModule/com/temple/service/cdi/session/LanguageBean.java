package com.temple.service.cdi.session;

import java.io.Serializable;

import com.temple.service.cdi.TempleCDIBean;
import com.temple.util.human.Language;
import com.temple.view.LocaleStringView;
import com.temple.view.LocaleViewable;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public interface LanguageBean extends TempleCDIBean, Serializable {

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
	String getString(String key, Serializable... objects);

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
	String getDetailedString(String key, Serializable... objects);

	/**
	 * TODOC
	 *
	 * @param lv
	 * @return
	 */
	LocaleStringView getStringView(LocaleViewable lv);
}
