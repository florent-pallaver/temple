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
public class RegionId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ToString
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false, updatable = false)
	private Country country;

	@ToString
	@Column(nullable = false, updatable = false)
	private int id;

	/**
	 * Constructor.
	 * TODOC
	 */
	public RegionId() {}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param country
	 * @param id
	 */
	public RegionId(Country country, int id) {
		super();
		this.country = country;
		this.id = id;
	}

	/**
	 * @return the country
	 */
	public Country getCountry() {
		return this.country;
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
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		final boolean b;
		if (this == obj) {
			b = true;
		} else if (obj instanceof RegionId) {
			final RegionId other = (RegionId) obj;
			b = this.country == other.country && this.id == other.id;
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
