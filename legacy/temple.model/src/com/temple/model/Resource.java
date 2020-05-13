package com.temple.model;

import com.temple.model.access.AccessRights;
import com.temple.view.View;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @param <AR>
 */
public interface Resource<AR extends AccessRights<?, ?>> extends TempleEntity, View {

	/**
	 * @return the {@link AccessRights} of this resource.
	 */
	AR getAccessRights();

	/**
	 * @return the owner (immutable) of this resource, it is never <code>null</code>.
	 */
	User<AR, ?> getOwner();

	/**
	 * @return the base {@link Group} (immutable) of this resource, it is never <code>null</code>
	 */
	Group<AR> getPrimaryGroup();
}
