package com.temple.model.geo;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.temple.util.TempleUtil;
import com.temple.util.ToString;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
@Embeddable
public class LocationId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ToString
	@Column(nullable = false, updatable = false)
	private int country;

	@ToString
	@Column(updatable = false)
	private Integer region;

	@ToString
	@Column(updatable = false)
	private Integer city;

	/**
	 * Constructor.
	 */
	public LocationId() {}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param countryCode
	 */
	public LocationId(int countryCode) {
		this(countryCode, null, null);
	}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param countryCode
	 * @param regionCode
	 */
	public LocationId(int countryCode, Integer regionCode) {
		this(countryCode, regionCode, null);
	}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param countryCode
	 * @param regionCode
	 * @param cityCode
	 */
	public LocationId(int countryCode, Integer regionCode, Integer cityCode) {
		super();
		this.country = countryCode;
		this.region = regionCode;
		this.city = cityCode;
	}

	/**
	 * @return the country
	 */
	public int getCountry() {
		return this.country;
	}

	/**
	 * @return the region
	 */
	public Integer getRegion() {
		return this.region;
	}

	/**
	 * @return the city
	 */
	public Integer getCity() {
		return this.city;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.country;
		result = prime * result + Objects.hash(this.region, this.city);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		final boolean b;
		if (this == obj) {
			b = true;
		} else if (obj instanceof LocationId) {
			final LocationId other = (LocationId) obj;
			b = this.country == other.country && Objects.equals(this.region, other.region) && Objects.equals(this.city, other.city);
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
