package com.temple.geo.model.geonames.filter;

import javax.persistence.metamodel.SingularAttribute;

import com.temple.geo.model.geonames.AbstractCountryArea;
import com.temple.geo.model.geonames.AbstractCountryArea_;
import com.temple.geo.model.geonames.AdministrativeDivision;
import com.temple.geo.model.geonames.PopulatedPlace;
import com.temple.model.filter.NamedEntity;

/**
 * TODOC
 *
 * @author flominou
 * @param <GE>
 */
public class SimpleCountryAreaFilter<GE extends AbstractCountryArea>
		extends AbstractCountryAreaFilter<GE, NamedEntity> {

	private static final long serialVersionUID = 1L;

	// CDI
	SimpleCountryAreaFilter() {
		this(null);
	}

	/**
	 * Constructor
	 *
	 * @param geoEntityClass
	 */
	public SimpleCountryAreaFilter(Class<GE> geoEntityClass) {
		super(geoEntityClass);
	}

	@Override
	public Class<NamedEntity> getResultClass() {
		return NamedEntity.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected SingularAttribute<? super GE, ?>[] getSelectedAttributes() {
		return (SingularAttribute<? super GE, ?>[]) new SingularAttribute<?, ?>[] { AbstractCountryArea_.id,
				AbstractCountryArea_.name };
	}

	/**
	 *
	 * @return
	 */
	public static final SimpleCountryAreaFilter<AdministrativeDivision> adminDivFilter() {
		return new SimpleCountryAreaFilter<>(AdministrativeDivision.class);
	}

	/**
	 *
	 * @return
	 */
	public static final SimpleCountryAreaFilter<PopulatedPlace> populatedPlaceFilter() {
		return new SimpleCountryAreaFilter<>(PopulatedPlace.class);
	}

}
