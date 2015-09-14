package com.temple.model.geo;

import com.temple.util.TempleUtil;
import com.temple.util.ToString;
import com.temple.util.json.AbstractJsonable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Base implementation class for geographic entities.
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
@MappedSuperclass
public abstract class AbstractGeoEntity extends AbstractJsonable implements GeoEntity {

	private static final long serialVersionUID = 1L;

	// Global revision date: 1 November 2013
	private static final Timestamp creationTS;

	static {
		final Calendar instance = Calendar.getInstance();
		instance.set(2015, 4, 1);
		creationTS = new Timestamp(instance.getTimeInMillis());
	}
	
	@ToString
	@Column(nullable = false, insertable = false, updatable = false)
	private String name;

	 @ToString
	 @Column(nullable = false, insertable = false, updatable = false)
	private double longitude;

	 @ToString
	 @Column(nullable = false, insertable = false, updatable = false)
	private double latitude;

	 @ToString
	 @Column(nullable = false, insertable = false, updatable = false)
	private int altitude;

	protected AbstractGeoEntity() {}

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

}
