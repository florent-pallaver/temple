package com.temple.model;

import java.io.Serializable;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public interface IdentifierGenerator {

	/**
	 * @return generate a new unique identifier for the given {@link TempleEntity}.
	 */
	Serializable newId(TempleEntity c);
}
