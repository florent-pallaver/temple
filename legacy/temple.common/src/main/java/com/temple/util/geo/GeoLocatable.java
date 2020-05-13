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
	
	/**
	 * @return {@code true} if this geographically located object is located inside a {@link Country}, {@code false} otherwise.
	 */
//	boolean isInsideCountry() ;
	
	/**
	 * 
	 * @return the {@link Country} this located object is inside, {@code null} if this located object is not in any {@link Country}.
	 */
//	Country getCountry() ;
}
