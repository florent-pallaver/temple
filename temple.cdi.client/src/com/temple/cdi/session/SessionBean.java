package com.temple.cdi.session;

import java.io.Serializable;

import com.temple.model.TempleUser;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public interface SessionBean extends Serializable {

	/**
	 * TODOC
	 * 
	 * @return
	 */
	TempleUser getUser();

	/**
	 * TODOC
	 * 
	 * @param u
	 */
	void setUser(TempleUser u);

	/**
	 * TODOC
	 * 
	 * @return
	 */
	boolean isSignedIn();

	/**
	 * TODOC
	 * 
	 * @param key
	 * @return
	 */
	Object get(String key);

	/**
	 * TODOC
	 * 
	 * @param key
	 * @param value
	 */
	void set(String key, Object value);
}
