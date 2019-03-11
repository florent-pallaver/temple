package com.temple.model;

import com.temple.model.access.AccessRights;
import com.temple.util.Nameable;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public interface Group<AR extends AccessRights<?, ?>> extends Resource<AR>, Nameable {
	/**
	 * @return the number of member in this group.
	 */
	// int getMemberCount();
}
