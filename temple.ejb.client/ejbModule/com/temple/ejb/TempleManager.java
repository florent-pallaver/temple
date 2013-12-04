package com.temple.ejb;

import java.io.Serializable;

/**
 * Base interface for all session EJB's.
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public interface TempleManager extends Serializable {

	/**
	 * Does nothing.
	 * <p>
	 * Use only to test connection with this session EJB.
	 */
	void touch();
}
