package com.temple.util.geo;


/**
 * Enumeration of all countries in the world!
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public enum Country implements GeoLocatable {
	AUSTRALIA("Australia", "AU");

	private final String name;

	private final String isoCode;

	private final double longitude;

	private final double latitude;

	private final int altitude;

	private Country(String name, String isoCode) {
		this(name, isoCode, 0.0, 0.0, 0);
	}

	private Country(String name, String isoCode, double longitude, double latitude, int altitude) {
		this.name = name;
		this.isoCode = isoCode;
		this.longitude = longitude;
		this.latitude = latitude;
		this.altitude = altitude;
	}

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

	/**
	 * @return the ISO code of this country.
	 */
	public String getIsoCode() {
		return this.isoCode;
	}
}
