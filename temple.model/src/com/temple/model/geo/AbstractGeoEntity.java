package com.temple.model.impl.geo;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.temple.model.geo.GeoEntity;
import com.temple.util.TempleUtil;
import com.temple.util.ToString;

/**
 * Base class for geo entities.
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
@MappedSuperclass
public abstract class AbstractGeoEntity implements GeoEntity {

	private static final long serialVersionUID = 1L;

	// Global revision date: 1 November 2013
	private static final Timestamp creationTS;

	@ToString
	@Column(nullable = false, insertable = false, updatable = false)
	private String name;

	// @ToString
	@Transient
	// @Column(nullable = false, insertable = false, updatable = false)
	private transient double longitude;

	// @ToString
	@Transient
	// @Column(nullable = false, insertable = false, updatable = false)
	private transient double latitude;

	// @ToString
	@Transient
	// @Column(nullable = false, insertable = false, updatable = false)
	private transient int altitude;

	AbstractGeoEntity() {}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public double getLongitude() {
		return this.longitude;
	}

	@Override
	public double getLatitude() {
		return this.latitude;
	}

	@Override
	public int getAltitude() {
		return this.altitude;
	}

	@Override
	public Timestamp getCreationTS() {
		return AbstractGeoEntity.creationTS;
	}

	@Override
	public int hashCode() {
		return 31 + Objects.hash(this.getId());
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj || this.getClass().isInstance(obj) && Objects.equals(this.getId(), ((AbstractGeoEntity) obj).getId());
	}

	@Override
	public String toString() {
		return TempleUtil.toString(this);
	}

	static {
		final Calendar instance = Calendar.getInstance();
		instance.set(2013, 10, 1);
		creationTS = new Timestamp(instance.getTimeInMillis());
	}
}
