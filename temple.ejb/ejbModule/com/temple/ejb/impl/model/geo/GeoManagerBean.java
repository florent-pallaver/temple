package com.temple.ejb.impl.model.geo;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import com.temple.ejb.impl.model.AbstractEntityManagerBean;
import com.temple.ejb.model.FindEntityException;
import com.temple.ejb.model.geo.GeoManager;
import com.temple.model.geo.City;
import com.temple.model.geo.CityId;
import com.temple.model.geo.GeoEntity;
import com.temple.model.geo.Region;
import com.temple.model.geo.RegionId;
import com.temple.model.impl.geo.CityImpl;
import com.temple.model.impl.geo.CityImpl_;
import com.temple.model.impl.geo.RegionImpl;
import com.temple.model.impl.geo.RegionImpl_;
import com.temple.util.geo.Country;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
@Stateless
@Local(GeoManager.class)
public class GeoManagerBean extends AbstractEntityManagerBean implements GeoManager {

	private static final long serialVersionUID = 1L;

	@Override
	public Region findRegion(RegionId regionId) throws FindEntityException {
		return this.find(RegionImpl.class, regionId);
	}

	@Override
	public Region[] findRegions(final Country country) throws FindEntityException {
		final List<RegionImpl> regions = this.find(RegionImpl.class, new WhereAlgo<RegionImpl>() {

			@Override
			public Predicate getWherePredicate(CriteriaBuilder cb, Path<RegionImpl> root) {
				return cb.equal(root.get(RegionImpl_.country), country);
			}
		});
		return regions.toArray(new Region[regions.size()]);
	}

	@Override
	public City findCity(CityId cityId) throws FindEntityException {
		return this.find(CityImpl.class, cityId);
	}

	@Override
	public City[] findCities(final RegionId regionId) throws FindEntityException {
		final List<CityImpl> cities = this.find(CityImpl.class, new WhereAlgo<CityImpl>() {

			@Override
			public Predicate getWherePredicate(CriteriaBuilder cb, Path<CityImpl> root) {
				return cb.equal(root.get(CityImpl_.region).get(RegionImpl_.id), regionId);
			}
		});
		return cities.toArray(new City[cities.size()]);
	}

	public <GE extends GeoEntity> List<GE> find(Class<GE> c, WhereAlgo<GE> algo) throws FindEntityException {
		final CriteriaQuery<GE> cq = this.cb.createQuery(c);
		cq.where(algo.getWherePredicate(this.cb, cq.from(c)));
		try {
			return this.em.createQuery(cq).getResultList();
		} catch (final Exception e) {
			throw new FindEntityException(CityImpl.class, e);
		}
	}

	private <GE extends GeoEntity> GE find(Class<GE> c, Serializable id) throws FindEntityException {
		try {
			return this.em.find(c, id);
		} catch (final Exception e) {
			throw new FindEntityException(c, id, e);
		}
	}
}
