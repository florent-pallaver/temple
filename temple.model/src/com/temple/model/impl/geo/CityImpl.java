package com.temple.model.impl.geo;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.temple.model.geo.City;
import com.temple.model.geo.CityId;
import com.temple.model.geo.Region;
import com.temple.util.ToString;

/**
 * {@link City} entity implementation.
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
@Entity
@Table(name = "city", indexes = @Index(name = CityImpl.indexName, columnList = CityImpl.indexColumnList))
public class CityImpl extends AbstractGeoEntity implements City {

	private static final long serialVersionUID = 1L;

	static final String indexName = "City_index_regionId_name";

	static final String indexColumnList = "regionId, name";

	@ToString
	@EmbeddedId
	private CityId id;

	@ToString
	@ManyToOne(optional = false)
	@JoinColumns({ @JoinColumn(name = "country", referencedColumnName = "country", insertable = false, updatable = false),
			@JoinColumn(name = "regionId", referencedColumnName = "id", insertable = false, updatable = false) })
	private RegionImpl region;

	@ToString
	@Column(nullable = false, insertable = false, updatable = false)
	private String postCode;

	CityImpl() {}

	@Override
	public CityId getId() {
		return this.id;
	}

	@Override
	public Region getRegion() {
		return this.region;
	}

	@Override
	public String getPostCode() {
		return this.postCode;
	}
}
