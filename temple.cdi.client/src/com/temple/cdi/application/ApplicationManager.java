package com.temple.cdi.application;

import com.temple.model.TempleUser;
import com.temple.view.LocaleBundle;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public interface ApplicationManager {

	/**
	 * TODOC
	 * 
	 * @return
	 */
	LocaleBundle getLocaleBundle();

	/**
	 * TODOC
	 * 
	 * @param u
	 */
	void userSignedIn(TempleUser u);

	/**
	 * TODOC
	 * 
	 * @param userId
	 */
	void userSignedOut(Integer userId);
}
