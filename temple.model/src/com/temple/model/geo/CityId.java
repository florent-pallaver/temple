package com.temple.model.geo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.temple.util.TempleUtil;
import com.temple.util.ToString;
import com.temple.util.geo.Country;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
@Embeddable
public class CityId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ToString
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false, updatable = false)
	private Country country;

	@ToString
	@Column(nullable = false, updatable = false)
	private int regionId;

	@ToString
	@Column(nullable = false, updatable = false)
	private int id;

	/**
	 * Constructor.
	 * TODOC
	 */
	public CityId() {}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param country
	 * @param regionId
	 * @param id
	 */
	public CityId(Country country, int regionId, int id) {
		super();
		this.country = country;
		this.regionId = regionId;
		this.id = id;
	}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param regionId
	 * @param id
	 */
	public CityId(RegionId regionId, int id) {
		super();
		this.country = regionId.getCountry();
		this.regionId = regionId.getId();
		this.id = id;
	}

	/**
	 * @return the country
	 */
	public Country getCountry() {
		return this.country;
	}

	/**
	 * @return the regionId
	 */
	public int getRegionId() {
		return this.regionId;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (this.country == null ? 0 : this.country.hashCode());
		result = prime * result + this.id;
		result = prime * result + this.regionId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		final boolean b;
		if (this == obj) {
			b = true;
		} else if (obj instanceof CityId) {
			final CityId other = (CityId) obj;
			b = this.country == other.country && this.id == other.id && this.regionId == other.regionId;
		} else {
			b = false;
		}
		return b;
	}

	@Override
	public String toString() {
		return TempleUtil.toString(this);
	}
}
