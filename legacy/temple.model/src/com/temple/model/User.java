package com.temple.model;

import com.temple.model.access.AccessRights;
import com.temple.model.access.Role;

/**
 * TODOC An user is also a group.
 * <p>
 * Note : a User is always a member of its own group.
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public interface User<AR extends AccessRights<?, ?>, R extends Role> extends Group<AR>, TempleUser {

	/**
	 * @return this user's mail address.
	 */
	String getEmail();

	/**
	 * @return the {@link Role} of this user.
	 */
	R getRole();
	/**
	 * @return the last time this user connected.
	 */
	// Calendar getLastLogInTime();
	/**
	 * @return the number of times this user connected.
	 */
	// int getConnectionCount();
	/* @return this user's favorites. Set<? extends Resource> getFavorites(); */
}
