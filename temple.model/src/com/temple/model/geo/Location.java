package com.temple.model.geo;

/**
 * Base contract to define a "broad location" that can either be a country, a region/state or a city.
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public interface Location extends GeoEntity {

	@Override
	LocationId getId();

	/**
	 * @return <code>true</code> if this Location is a country, <code>false</code> otherwise
	 */
	boolean isCountry();

	/**
	 * @return <code>true</code> if this Location is a region/state, <code>false</code> otherwise
	 */
	boolean isRegion();

	/**
	 * @return <code>true</code> if this Location is a city, <code>false</code> otherwise
	 */
	boolean isCity();
}
