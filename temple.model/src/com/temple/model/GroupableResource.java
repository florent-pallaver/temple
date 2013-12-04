package com.temple.model;

import com.temple.model.access.AccessRights;

/**
 * Base contract for a {@link Resource} to be allowed to be a member of more than one group.
 * 
 * @author Florent Pallaver
 * @param <AR>
 */
public interface GroupableResource<AR extends AccessRights<?, ?>> extends Resource<AR> {

	/**
	 * @return the secondary {@link Group}s count this resource is member of.
	 */
	int getSecondaryGroupsCount();

	/**
	 * Sets the secondary {@link Group}s count this resource is member of.
	 * 
	 * @param count - the count to set.
	 */
	void setSecondaryGroupsCount(int count);
}
