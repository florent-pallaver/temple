package com.temple.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public interface TempleEntity extends Serializable {

	/**
	 * @return the unique identifier (immutable) of this entity.
	 */
	Serializable getId();

	/**
	 * @return the creation date (immutable) of this resource, it is never <code>null</code>.
	 */
	Timestamp getCreationTS();
}
