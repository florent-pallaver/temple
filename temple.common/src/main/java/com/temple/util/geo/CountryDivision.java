package com.temple.util.geo;

/**
 * TODOC
 * @author flominou
 */
public interface CountryDivision extends CountryArea {

	@Override
	default GeoArea getParentArea() {
		return this.isFirstLevelDivision() ? this.getCountry() : this.getParentDivision() ;
	}
	
	/**
	 * Tells whether this {@link CountryDivision} is a first level division. That is there are none above this one.
	 * <br>
	 * For example, a state for the United States or Australia, a region for France ...
	 * @return {@code true} if this is a first level division, {@code false} otherwise.
	 */
	default boolean isFirstLevelDivision() {
		return this.getParentDivision() == null ;
	}
	
}
