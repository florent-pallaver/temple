package com.temple.model.geo;

/**
 * Base contract for an object to be used as a city.
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public interface City extends GeoEntity {

	@Override
	CityId getId();

	/**
	 * @return the {@link Region} this city is in.
	 */
	Region getRegion();

	/**
	 * @return the post code of this city.
	 */
	String getPostCode();
}
