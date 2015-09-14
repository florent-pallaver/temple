package com.temple.model.geo.geonames.filter;

import com.temple.model.filter.AbstractDynamicFilter;
import com.temple.model.geo.geonames.AbstractCountryArea;
import com.temple.model.geo.geonames.AbstractCountryArea_;
import com.temple.model.geo.geonames.AdministrativeDivision;
import com.temple.model.geo.geonames.Feature;
import com.temple.util.TempleLogger;
import com.temple.util.ToString;
import com.temple.util.geo.Country;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * TODOC
 * @author flominou
 * @param <GE>
 */
public class CountryAreaFilter<GE extends AbstractCountryArea> extends AbstractDynamicFilter<GE>{

	private static final long serialVersionUID = 1L;

	@ToString
	private final Class<? extends GE> geoEntityClass ;

	@ToString
	private Country country ;
	
	@ToString
	private Feature[] features ;
	
	@ToString
	private Integer parentId ;

	// CDI
	CountryAreaFilter() {
		this(null) ;
	}
	
	/**
	 * Constructor
	 * @param geoEntityClass
	 */
	public CountryAreaFilter(Class<GE> geoEntityClass) {
		super();
		this.geoEntityClass = geoEntityClass ;
		this.setOrder(NameOrder.ASC);
	}

	@Override
	protected Class<? extends GE> getEntityClass() {
		return this.geoEntityClass ;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Feature[] getFeatures() {
		return this.features.clone() ;
	}
	
	public void setFeatures(Feature... features) {
		this.features = features ;
	}
	
	public Integer getParentId() {
		return parentId;
	}

	public void setParent(AdministrativeDivision parent) {
		this.parentId = parent == null ? null : parent.getId();
	}
	
	public void setParentId(Integer parentId) {
		new TempleLogger().info(" *************** " + parentId);
		this.parentId = parentId ;
	}
	
	@Override
	protected Predicate createWherePredicate(CriteriaBuilder cb, Root<? extends GE> root) {
		Predicate p = cb.conjunction() ;
		if(parentId == null) {
			if(this.country != null) {
				p = cb.and(cb.equal(root.get(AbstractCountryArea_.country), this.country)) ;
			}
			if(this.features != null && this.features.length > 0) {
				p = cb.and(p, root.get(AbstractCountryArea_.feature).in((Object[]) this.features)) ;
			}
		} else {
			p = cb.equal(root.get(AbstractCountryArea_.parentId), this.parentId) ;
		}
		return p ;
	}
	
}
