package com.temple.service.ejb.model.geo;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import com.temple.model.geo.Location;
import com.temple.model.geo.LocationId;
import com.temple.model.geo.LocationId_;
import com.temple.model.impl.geo.LocationImpl;
import com.temple.model.impl.geo.LocationImpl_;
import com.temple.service.ejb.model.AbstractEntityManagerBean;
import com.temple.service.geo.LocationManager;
import com.temple.service.model.FindEntityException;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
@Stateless
@Local(LocationManager.class)
public class LocationManagerBean extends AbstractEntityManagerBean implements LocationManager {

	private static final long serialVersionUID = 1L;

	@Override
	public Location[] getCountries() throws FindEntityException {
		return this.get(new WhereAlgo<LocationId>() {

			@Override
			public Predicate getWherePredicate(CriteriaBuilder cb, Path<LocationId> root) {
				return cb.and(cb.isNull(root.get(LocationId_.region)), cb.isNull(root.get(LocationId_.city)));
			}
		});
	}

	@Override
	public Location[] getRegions(final int countryId) throws FindEntityException {
		return this.get(new WhereAlgo<LocationId>() {

			@Override
			public Predicate getWherePredicate(CriteriaBuilder cb, Path<LocationId> root) {
				return cb.and(cb.equal(root.get(LocationId_.country), Integer.valueOf(countryId)), cb.isNotNull(root.get(LocationId_.region)),
						cb.isNull(root.get(LocationId_.city)));
			}
		});
	}

	@Override
	public Location[] getCities(final int countryId, final int regionId) throws FindEntityException {
		return this.get(new WhereAlgo<LocationId>() {

			@Override
			public Predicate getWherePredicate(CriteriaBuilder cb, Path<LocationId> root) {
				return cb.and(cb.equal(root.get(LocationId_.country), Integer.valueOf(countryId)),
						cb.equal(root.get(LocationId_.region), Integer.valueOf(regionId)), cb.isNotNull(root.get(LocationId_.city)));
			}
		});
	}

	private Location[] get(WhereAlgo<LocationId> algo) throws FindEntityException {
		final CriteriaQuery<LocationImpl> cq = this.cb.createQuery(LocationImpl.class);
		final Path<LocationId> idPath = cq.from(LocationImpl.class).get(LocationImpl_.id);
		cq.where(algo.getWherePredicate(this.cb, idPath));
		try {
			final List<LocationImpl> results = this.em.createQuery(cq).getResultList();
			return results.toArray(new Location[results.size()]);
		} catch (final Exception e) {
			throw new FindEntityException(LocationImpl.class, e);
		}
	}

	@Override
	public Location find(LocationId lid) throws FindEntityException {
		try {
			return this.em.find(LocationImpl.class, lid);
		} catch (final Exception e) {
			throw new FindEntityException(LocationImpl.class, lid);
		}
	}
}
