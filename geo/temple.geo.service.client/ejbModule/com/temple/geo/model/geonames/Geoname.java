package com.temple.geo.model.geonames;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.temple.geo.model.GeoEntity;
import com.temple.util.calendar.CalendarUtil;
import com.temple.util.geo.Country;
import com.temple.util.json.AbstractJsonable;
import com.temple.util.json.JsonField;

/**
 *
 * @author flominou
 */
@Entity
@Table(name = "geonames", schema = "locations")
public class Geoname extends AbstractJsonable implements GeoEntity {

	private static final long serialVersionUID = 1L;

	@JsonField
	@Id
	@Column(nullable = false, updatable = false)
	private int id;

	@JsonField
	@Column(nullable = false, length = 200)
	private String name;

	// @JsonField
	// @Column(nullable = false, length = 200, insertable = false, updatable =
	// false)
	// private String asciiname;

	@JsonField
	@Column(nullable = false, precision = 7, scale = 5)
	private double latitude;

	@JsonField
	@Column(nullable = false, precision = 8, scale = 5)
	private double longitude;

	@JsonField
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private FeatureType featureType;

	@JsonField
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private Feature feature;

	@JsonField
	@Enumerated(EnumType.ORDINAL)
	private Country country;

	// @JsonField
	// @Column(length = 60, insertable = false, updatable = false)
	// private String cc2;

	// @JsonField
	@Column(nullable = false, length = 20)
	private String ac1;

	// @JsonField
	@Column(nullable = false, length = 80)
	private String ac2;

	// @JsonField
	@Column(nullable = false, length = 20)
	private String ac3;

	// @JsonField
	@Column(nullable = false, length = 20)
	private String ac4;

	@JsonField
	@Column(nullable = false)
	private long population;

	@JsonField
	@Column(nullable = false)
	private int elevation;

	// @JsonField(handler = IntegerHandler.class)
	@Column(nullable = false)
	private int dem;

	@JsonField
	@Column(length = 40)
	private String timezone;

	@JsonField(inputable = false)
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Calendar modification_date;

	@Transient
	private transient Timestamp ts;

	protected Geoname() {
	}

	public Geoname(String s) {
		this(s, "\t");
	}

	public Geoname(String s, String delimiter) {
		this(s.split(delimiter));
	}

	private Geoname(String[] tuple) {
		this(Integer.parseInt(tuple[0]), tuple[1],
				// tuple[2],
				Double.parseDouble(tuple[4]), Double.parseDouble(tuple[5]),
				// tuple[6],
				Geoname.parseFeature(tuple[7]), Country.getByISOCode(tuple[8], false),
				// tuple[9],
				tuple[10], tuple[11], tuple[12], tuple[13], Geoname.parseLong(tuple[14]), Geoname.parseInt(tuple[15]), Geoname.parseInt(tuple[16]), tuple[17],
				Geoname.parseCalendar(tuple[18]));
	}

	private static Calendar parseCalendar(String s) {
		final Calendar c = Calendar.getInstance();
		try {
			c.setTime(CalendarUtil.DATE_FORMAT.parse(s));
		} catch (final ParseException ex) {
			throw new RuntimeException(ex);
		}
		return c;
	}

	private static int parseInt(String s) {
		return s.isEmpty() ? 0 : Integer.parseInt(s);
	}

	private static long parseLong(String s) {
		return s.isEmpty() ? 0 : Long.parseLong(s);
	}

	private static Feature parseFeature(String feature_code) {
		Feature f = Feature.UNKNOWN;
		try {
			f = Feature.valueOf(feature_code);
		} catch (final IllegalArgumentException e) {
			// ignored ...
		}
		return f;
	}

	private Geoname(int geonameid, String name, double latitude, double longitude, Feature feature, Country country, String admin1_code, String admin2_code,
			String admin3_code, String admin4_code, long population, int elevation, int dem, String timezone, Calendar modification_date) {
		this.id = geonameid;
		this.name = name;
		// this.asciiname = asciiname;
		this.latitude = latitude;
		this.longitude = longitude;
		this.featureType = feature.getType();
		this.feature = feature;
		this.country = country;
		// this.cc2 = cc2;
		this.ac1 = admin1_code;
		this.ac2 = admin2_code;
		this.ac3 = admin3_code;
		this.ac4 = admin4_code;
		this.population = population;
		this.elevation = elevation;
		this.dem = dem;
		this.timezone = timezone;
		this.modification_date = modification_date;
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public Timestamp getCreationTS() {
		if (this.ts == null) {
			this.ts = new Timestamp(this.modification_date.getTimeInMillis());
		}
		return this.ts;
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
		return this.elevation;
	}

	@Override
	public String getName() {
		return this.name;
	}

	public String getAc1() {
		return this.ac1;
	}

	public String getAc2() {
		return this.ac2;
	}

	public String getAc3() {
		return this.ac3;
	}

	public String getAc4() {
		return this.ac4;
	}

	public Country getCountry() {
		return this.country;
	}

	public FeatureType getFeatureType() {
		return this.featureType;
	}

	public Feature getFeature() {
		return this.feature;
	}

	@Override
	public int hashCode() {
		return 219 + this.id;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Geoname && this.id == ((Geoname) obj).id;
	}

	@Override
	public String toString() {
		return this.toJsonObject().toString();
	}

}
