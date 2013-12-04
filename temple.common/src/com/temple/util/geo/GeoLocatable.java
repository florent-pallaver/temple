package com.temple.util.geo;

import com.temple.util.Nameable;

/**
 * Base contract for an object to be geographically locatable.
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public interface GeoLocatable extends Nameable {

	/**
	 * @return the longitude of this object.
	 */
	double getLongitude();

	/**
	 * @return the latitude of this object.
	 */
	double getLatitude();

	/**
	 * @return the altitude (in meters) of this object.
	 */
	int getAltitude();
}
