package com.temple.geo.model.geonames.filter;

import java.io.Serializable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import com.temple.geo.model.geonames.AbstractCountryArea;
import com.temple.geo.model.geonames.AbstractCountryArea_;
import com.temple.geo.model.geonames.AdministrativeDivision;
import com.temple.geo.model.geonames.Feature;
import com.temple.model.filter.AbstractDynamicPageableFilter;
import com.temple.util.ToString;
import com.temple.util.geo.Country;

/**
 * TODOC
 *
 * @author flominou
 * @param <GE>
 */
public abstract class AbstractCountryAreaFilter<GE extends AbstractCountryArea, R extends Serializable>
		extends AbstractDynamicPageableFilter<GE, R> {

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
	AbstractCountryAreaFilter() {
		this(null);
	}

	/**
	 * Constructor
	 *
	 * @param geoEntityClass
	 */
	protected AbstractCountryAreaFilter(Class<GE> geoEntityClass) {
		super();
		this.geoEntityClass = geoEntityClass;
		this.setOrder(NameOrder.ASC);
	}

	@Override
	public Class<GE> getEntityClass() {
		return this.geoEntityClass;
	}

	public Country getCountry() {
		return this.country;
	}

	public AbstractCountryAreaFilter<GE, R> setCountry(Country country) {
		this.country = country;
		return this;
	}

	public Feature[] getFeatures() {
		return this.features.clone();
	}

	public AbstractCountryAreaFilter<GE, R> setFeatures(Feature... features) {
		this.features = features;
		return this;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public AbstractCountryAreaFilter<GE, R> setParent(AdministrativeDivision parent) {
		this.parentId = parent == null ? null : parent.getId();
		return this;
	}

	public AbstractCountryAreaFilter<GE, R> setParentId(Integer parentId) {
		this.parentId = parentId;
		return this;
	}

	@Override
	protected Predicate createWherePredicate(CriteriaBuilder cb, Root<? extends GE> root, CriteriaQuery<?> rootQuery) {
		Predicate p = cb.conjunction();
		if (this.parentId == null || this.parentId == 0) {
			if (this.country != null) {
				p = cb.and(cb.equal(root.get(AbstractCountryArea_.country), this.country));
			}
		} else {
			p = cb.equal(root.get(AbstractCountryArea_.parentId), this.parentId);
		}
		if (this.features != null && this.features.length > 0) {
			p = cb.and(p, root.get(AbstractCountryArea_.feature).in((Object[]) this.features));
		}
		return p;
	}

	@Override
	protected SingularAttribute<? super GE, ?> getCountedField() {
		return AbstractCountryArea_.id;
	}

}
