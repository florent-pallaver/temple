package com.temple.util.geo;

/**
 * TODOC
 * @author flominou
 */
public interface HumanSettlement extends CountryArea {

	@Override
	default GeoArea getParentArea() {
		return this.getParentDivision() ;
	}
	
	/**
	 * @return {@code true} if this is the seat of its {@link #getCountry() country}, {@code false} otherwise.
	 */
	boolean isCapital() ;
	
	/**
	 * @return {@code true} if this is the seat of its {@link #getParentArea() parent country division}, {@code false} otherwise.
	 */
	boolean isSeat();
	
}
