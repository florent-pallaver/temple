package com.temple.geo.model.geonames.filter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.temple.geo.model.geonames.AbstractCountryArea;
import com.temple.geo.model.geonames.AbstractCountryArea_;
import com.temple.geo.model.geonames.AdministrativeDivision;
import com.temple.geo.model.geonames.Feature;
import com.temple.geo.model.geonames.PopulatedPlace;
import com.temple.model.filter.AbstractDynamicFilter;
import com.temple.util.ToString;
import com.temple.util.geo.Country;

/**
 * TODOC
 *
 * @author flominou
 * @param <GE>
 */
public class CountryAreaFilter<GE extends AbstractCountryArea> extends AbstractDynamicFilter<GE, GE> {

	private static final long serialVersionUID = 1L;

	@ToString
	private final Class<GE> geoEntityClass;

	@ToString
	private Country country;

	@ToString
	private Feature[] features;

	@ToString
	private Integer parentId;

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
		super();
		this.geoEntityClass = geoEntityClass;
		this.setOrder(NameOrder.ASC);
	}

	@Override
	public Class<GE> getEntityClass() {
		return this.geoEntityClass;
	}

	@Override
	public Class<GE> getResultClass() {
		return this.geoEntityClass;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Feature[] getFeatures() {
		return this.features.clone();
	}

	public void setFeatures(Feature... features) {
		this.features = features;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParent(AdministrativeDivision parent) {
		this.parentId = parent == null ? null : parent.getId();
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Override
	protected Predicate createWherePredicate(CriteriaBuilder cb, Root<? extends GE> root, CriteriaQuery<?> rootQuery) {
		Predicate p = cb.conjunction();
		if (this.parentId == null) {
			if (this.country != null) {
				p = cb.and(cb.equal(root.get(AbstractCountryArea_.country), this.country));
			}
			if (this.features != null && this.features.length > 0) {
				p = cb.and(p, root.get(AbstractCountryArea_.feature).in((Object[]) this.features));
			}
		} else {
			p = cb.equal(root.get(AbstractCountryArea_.parentId), this.parentId);
		}
		return p;
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
