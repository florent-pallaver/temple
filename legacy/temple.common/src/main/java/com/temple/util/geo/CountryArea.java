package com.temple.util.geo;

/**
 * Factorizing interface
 * @author flominou
 */
public interface CountryArea extends GeoArea {
	
	/**
	 * @return the {@link Country} this {@link CountryDivision} is in. Never {@code null}.
	 */
	Country getCountry() ;
	
	/**
	 * @return the {@link CountryDivision} this {@link CountryDivision} is in, {@code null} if {@link #isFirstLevelDivision()} is {@code true}.
	 */
	CountryDivision getParentDivision();

	
	/**
	 * TODOC
	 * @param cd a {@link CountryDivision}
	 * @return {@code true} if this is inside the given {@link CountryDivision}, {@code false} otherwise.
	 */
//	boolean isInside(CountryDivision cd) {
//		CountryArea tcd = this.getParentDivision() ;
//		boolean b = false ;
//		do {
//			b = cd.equals(tcd) ;
//		} while (!b) ;
//		return b ;
//	}
	

	
}
