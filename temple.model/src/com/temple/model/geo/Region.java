package com.temple.model.geo;

import com.temple.util.geo.Country;

/**
 * Base contract for an object to be used as region or state.
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public interface Region extends GeoEntity {

	@Override
	RegionId getId();

	/**
	 * @return the {@link Country} this region is in.
	 */
	Country getCountry();
}
