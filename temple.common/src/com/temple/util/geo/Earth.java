package com.temple.util.geo;

/**
 * The Earth is naturally the upper top {@link GeoArea}.
 * <br>
 * It contains itself, that is {@link #getParentArea()} returns {@code this}.
 * @author flominou
 * @version 1.0
 * @see #instance
 */
public final class Earth implements GeoArea {
	
	/**
	 * Sole instance of this class.
	 */
	public static final Earth instance = new Earth() ;
	
	private Earth() {}

	@Override
	public GeoArea getParentArea() {
		// The earth is its own parent division
		return this ;
	}

	@Override
	public double getLongitude() {
		return 0.0d ;
	}

	@Override
	public double getLatitude() {
		return 0.0d ;
	}

	@Override
	public int getAltitude() {
		return 0 ;
	}

	@Override
	public String getName() {
		// FIXME localize !
		return "Earth" ;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Earth;
	}

	@Override
	public int hashCode() {
		return instance.hashCode() ;
	}
	
}
