package com.temple.model.impl.geo;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.Table;

import com.temple.model.geo.Region;
import com.temple.model.geo.RegionId;
import com.temple.util.ToString;
import com.temple.util.geo.Country;

/**
 * {@link Region} entity implementation.
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
@Entity
@Table(name = "region", indexes = @Index(name = RegionImpl.indexName, columnList = RegionImpl.indexColumnList))
public class RegionImpl extends AbstractGeoEntity implements Region {

	private static final long serialVersionUID = 1L;

	static final String indexName = "Region_index_country_name";

	static final String indexColumnList = "country, name";

	@ToString
	@EmbeddedId
	private RegionId id;

	@ToString
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false, insertable = false, updatable = false)
	private Country country;

	RegionImpl() {}

	@Override
	public RegionId getId() {
		return this.id;
	}

	@Override
	public Country getCountry() {
		return this.country;
	}
}
