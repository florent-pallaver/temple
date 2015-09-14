package com.temple.model.geo.geonames;

import com.temple.model.geo.GeoEntity;
import com.temple.util.TempleUtil;
import com.temple.util.ToString;
import com.temple.util.geo.Country;
import com.temple.util.geo.CountryArea;
import com.temple.util.json.AbstractJsonable;
import com.temple.util.json.DoubleHandler;
import com.temple.util.json.IntegerHandler;
import com.temple.util.json.JsonField;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * TODOC
 * @author flominou
 */
@MappedSuperclass
public abstract class AbstractCountryArea extends AbstractJsonable implements CountryArea, GeoEntity {

	private static final long serialVersionUID = 1L ;
	
	@ToString
	@JsonField(handler = IntegerHandler.class)
	@Id
	@Column(nullable = false, updatable = false)
	private int id ;
	
	@ToString
	@JsonField
	@Column(nullable = false, length = 200)
	private String name;

	@ToString
	@JsonField(handler = DoubleHandler.class)
	@Column(nullable = false, precision = 7, scale = 5)
	private double latitude;

	@ToString
	@JsonField(handler = DoubleHandler.class)
	@Column(nullable = false, precision = 8, scale = 5)
	private double longitude;

	@ToString
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	Feature feature;

	@ToString
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private Country country;

	@ManyToOne
	@JoinColumn(name = "parentId")
	private AdministrativeDivision parentDivision ;

	@Column(insertable = false, updatable = false)
	private int parentId ;

	@Transient
	private transient String lcName ;
	
	AbstractCountryArea() {
		super() ;
	}

	AbstractCountryArea(Geoname g, AdministrativeDivision parent) {
		this(g.getId(), g.getName(), g.getLatitude(), g.getLongitude(), g.getFeature(), g.getCountry(), parent) ;
	}
	
	AbstractCountryArea(int id, String name, double latitude, double longitude, Feature feature, Country country, AdministrativeDivision parentDivision) {
		this() ;
		this.id = id;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.feature = feature;
		this.country = country;
		this.parentDivision = parentDivision;
	}
	
	@Override
	public Integer getId() {
		return this.id ;
	}

	@Override
	public double getLongitude() {
		return this.longitude ;
	}

	@Override
	public double getLatitude() {
		return this.latitude ;
	}

	@Override
	public int getAltitude() {
		return 0 ;
	}

	@Override
	public String getName() {
		return this.name ;
	}

	@Override
	public Timestamp getCreationTS() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public Feature getFeature() {
		return this.feature;
	}
	
	@Override
	public Country getCountry() {
		return this.country ;
	}

	@Override
	public AdministrativeDivision getParentDivision() {
		return this.parentDivision ;
	}

	/**
	 * Tests if the lowercased name of this object starts with the given lowercased prefix.
	 * @param lcPrefix
	 * @return TODOC
	 */
	public boolean lowercaseNameStartsWith(String lcPrefix) {
		if(this.lcName == null) {
			this.lcName = this.name.toLowerCase() ;
		}
		return this.lcName.startsWith(lcPrefix) ;
	}
	
	@Override
	public int hashCode() {
		return 371 + this.id;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj != null && this.getClass() == obj.getClass() && this.id == ((AbstractCountryArea) obj).id;
	}

	@Override
	public String toString() {
		return TempleUtil.toString(this) ;
	}
	
}
