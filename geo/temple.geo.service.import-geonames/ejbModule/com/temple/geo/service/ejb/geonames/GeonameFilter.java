package com.temple.geo.service.ejb.geonames;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import com.temple.geo.model.geonames.Feature;
import com.temple.geo.model.geonames.FeatureType;
import com.temple.geo.model.geonames.Geoname;
import com.temple.geo.model.geonames.Geoname_;
import com.temple.model.filter.AbstractStaticPageableFilter;
import com.temple.util.ToString;
import com.temple.util.geo.Country;

/**
 * TODOC
 *
 * @author flominou
 */
final class GeonameFilter extends AbstractStaticPageableFilter<Geoname, Geoname> {

	private static final long serialVersionUID = 1L;

	@ToString
	private Country country = null;

	@ToString
	private FeatureType featureType = null;

	@ToString
	private List<Feature> features = new ArrayList<>();

	@ToString
	private String ac1 = null;

	@ToString
	private String ac2 = null;

	@ToString
	private String ac3 = null;

	@ToString
	private String ac4 = null;

	/**
	 * Constructor
	 */
	public GeonameFilter() {
		super(Geoname.class, Geoname.class);
		this.setMaxCountIgnored(true);
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public FeatureType getFeatureType() {
		return this.featureType;
	}

	public void setFeatureType(FeatureType featureType) {
		this.featureType = featureType;
	}

	public List<Feature> getFeatures() {
		return this.features;
	}

	public void setFeatures(List<Feature> features) {
		this.features = features;
	}

	public void setFeatures(Feature... features) {
		this.features.clear();
		Arrays.stream(features).forEach(f -> this.features.add(f));
	}

	public void setAdminCodes(String ac1) {
		this.setAdminCodes(ac1, null, null, null);
	}

	public void setAdminCodes(String ac1, String ac2) {
		this.setAdminCodes(ac1, ac2, null, null);
	}

	public void setAdminCodes(String ac1, String ac2, String ac3) {
		this.setAdminCodes(ac1, ac2, ac3, null);
	}

	public void setAdminCodes(String ac1, String ac2, String ac3, String ac4) {
		this.ac1 = ac1;
		this.ac2 = ac2;
		this.ac3 = ac3;
		this.ac4 = ac4;
	}

	@Override
	protected SingularAttribute<? super Geoname, ?> getCountedField() {
		return Geoname_.id;
	}

	@Override
	protected Predicate createWherePredicate(CriteriaBuilder cb, Root<? extends Geoname> root,
			CriteriaQuery<?> rootQuery) {
		Predicate p = cb.conjunction();
		if (this.country != null) {
			p = cb.and(cb.equal(root.get(Geoname_.country), this.country));
		}
		if (this.features.isEmpty()) {
			if (this.featureType != null) {
				p = cb.and(p, cb.equal(root.get(Geoname_.featureType), this.featureType));
			}
		} else {
			p = cb.and(p, root.get(Geoname_.feature).in(this.features));
		}
		if (this.ac1 != null && !this.ac1.isEmpty()) {
			p = cb.and(p, cb.equal(root.get(Geoname_.ac1), this.ac1));
			if (this.ac2 != null && !this.ac2.isEmpty()) {
				p = cb.and(p, cb.equal(root.get(Geoname_.ac2), this.ac2));
				if (this.ac3 != null && !this.ac3.isEmpty()) {
					p = cb.and(p, cb.equal(root.get(Geoname_.ac3), this.ac3));
					if (this.ac4 != null && !this.ac4.isEmpty()) {
						p = cb.and(p, cb.equal(root.get(Geoname_.ac4), this.ac4));
					}
				}
			}
		}
		return p;
	}

}
