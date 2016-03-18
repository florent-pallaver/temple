package com.temple.geo.model.geonames.filter;

import com.temple.geo.model.geonames.AbstractCountryArea;
import com.temple.geo.model.geonames.AdministrativeDivision;
import com.temple.geo.model.geonames.PopulatedPlace;

/**
 * TODOC
 *
 * @author flominou
 * @param <GE>
 */
public class CountryAreaFilter<GE extends AbstractCountryArea> extends AbstractCountryAreaFilter<GE, GE> {

	private static final long serialVersionUID = 1L;

	// CDI
	CountryAreaFilter() {
		this(null);
	}

	/**
	 * Constructor
	 *
	 * @param geoEntityClass
	 */
	public CountryAreaFilter(Class<GE> geoEntityClass) {
		super(geoEntityClass);
	}

	@Override
	public Class<GE> getResultClass() {
		return this.getEntityClass();
	}

	/**
	 *
	 * @return
	 */
	public static final CountryAreaFilter<AdministrativeDivision> adminDivFilter() {
		return new CountryAreaFilter<>(AdministrativeDivision.class);
	}

	/**
	 *
	 * @return
	 */
	public static final CountryAreaFilter<PopulatedPlace> populatedPlaceFilter() {
		return new CountryAreaFilter<>(PopulatedPlace.class);
	}

}
