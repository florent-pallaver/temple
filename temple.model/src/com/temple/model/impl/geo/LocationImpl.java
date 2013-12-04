package com.temple.model.impl.geo;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.temple.model.geo.Location;
import com.temple.model.geo.LocationId;
import com.temple.util.ToString;

/**
 * Default implementation of {@link Location}.
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
@Entity
@Table(name = "location")
public class LocationImpl extends AbstractGeoEntity implements Location {

	private static final long serialVersionUID = 1L;

	@ToString
	@EmbeddedId
	private LocationId id;

	LocationImpl() {}

	@Override
	public LocationId getId() {
		return this.id;
	}

	@Override
	public boolean isCountry() {
		return this.id.getRegion() == null && this.id.getCity() == null;
	}

	@Override
	public boolean isRegion() {
		return this.id.getRegion() != null && this.id.getCity() == null;
	}

	@Override
	public boolean isCity() {
		return this.id.getCity() != null;
	}
}
