package com.temple.model.geo;

import com.temple.util.ToString;
import com.temple.util.geo.Country;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * Base contract to define a "broad location" that can either be a country, a region/state or a city.
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
@Entity
public class Location extends AbstractGeoEntity {

	private static final long serialVersionUID = 1L;

	@ToString
	@EmbeddedId
	private LocationId id;

	protected Location() {
	}

	@Override
	public LocationId getId() {
		return this.id;
	}

//	@Override
	public Country getCountry() {
		return Country.values()[this.id.getCountry()] ;
	}

	/**
	 * @return <code>true</code> if this Location is a country, <code>false</code> otherwise
	 */
	public boolean isCountry() {
		return this.id.getRegion() == null && this.id.getCity() == null;
	}

	/**
	 * @return <code>true</code> if this Location is a region/state, <code>false</code> otherwise
	 */
	public boolean isRegion() {
		return this.id.getRegion() != null && this.id.getCity() == null;
	}

	/**
	 * @return <code>true</code> if this Location is a city, <code>false</code> otherwise
	 */
	public boolean isCity() {
		return this.id.getCity() != null;
	}
}
