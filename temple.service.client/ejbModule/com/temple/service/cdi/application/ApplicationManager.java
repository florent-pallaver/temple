package com.temple.service.cdi.application;

import com.temple.model.TempleUser;

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
