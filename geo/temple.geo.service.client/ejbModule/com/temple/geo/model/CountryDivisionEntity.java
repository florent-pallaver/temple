package com.temple.geo.model;

import com.temple.util.geo.CountryDivision;

/**
 * Util interface extending {@link CountryDivision} and {@link GeoEntity}.
 *
 * @author flominou
 * @version 1.0
 */
public interface CountryDivisionEntity extends CountryDivision, GeoEntity {

	@Override
	Integer getId() ;

}
