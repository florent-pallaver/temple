package com.temple.service.cdi;

import java.util.List;

import com.temple.util.human.Language;
import com.temple.view.LocaleBundle;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public interface CDIConfiguration {

	/**
	 * TODOC
	 * 
	 * @return
	 */
	LocaleBundle getLocaleBundle();

	/**
	 * TODOC
	 * 
	 * @return
	 */
	Language getDefaultLanguage();

	/**
	 * TODOC
	 * 
	 * @return
	 */
	List<Language> getSupportedLanguages();
}
