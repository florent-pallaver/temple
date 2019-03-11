package com.temple.util.geo;

/**
 * Base contract for an object to be used as a geographic area.
 * <br>
 * A geographic area is a defined area on earth.  Its geolocation is its geographic center.
 * 
 * @author flominou
 */
public interface GeoArea extends GeoLocatable {

	/**
	 * @return the {@link GeoArea} this area is in. Never {@code null}.
	 */
	GeoArea getParentArea();
	
}
