package com.temple.service.ejb.model.geo;

import com.temple.model.geo.Location;
import com.temple.model.geo.LocationId;
import com.temple.model.geo.LocationId_;
import com.temple.model.geo.Location_;
import com.temple.service.ejb.model.AbstractEntityManagerBean;
import com.temple.service.model.geo.LocationManager;
import com.temple.service.model.FindEntityException;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;

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
		return this.get((CriteriaBuilder cb1, Path<LocationId> root) -> cb1.and(cb1.isNull(root.get(LocationId_.region)), cb1.isNull(root.get(LocationId_.city))));
	}

	@Override
	public Location[] getRegions(final int countryId) throws FindEntityException {
		return this.get((CriteriaBuilder cb1, Path<LocationId> root) -> cb1.and(cb1.equal(root.get(LocationId_.country), countryId), cb1.isNotNull(root.get(LocationId_.region)), cb1.isNull(root.get(LocationId_.city))));
	}

	@Override
	public Location[] getCities(final int countryId, final int regionId) throws FindEntityException {
		return this.get((CriteriaBuilder cb1, Path<LocationId> root) -> cb1.and(cb1.equal(root.get(LocationId_.country), countryId), cb1.equal(root.get(LocationId_.region), regionId), cb1.isNotNull(root.get(LocationId_.city))));
	}

	private Location[] get(WhereAlgo<LocationId> algo) throws FindEntityException {
		final CriteriaQuery<Location> cq = this.cb.createQuery(Location.class);
		final Path<LocationId> idPath = cq.from(Location.class).get(Location_.id);
		cq.where(algo.getWherePredicate(this.cb, idPath));
		try {
			final List<Location> results = this.em.createQuery(cq).getResultList();
			return results.toArray(new Location[results.size()]);
		} catch (final Exception e) {
			throw new FindEntityException(Location.class, e);
		}
	}

	@Override
	public Location find(LocationId lid) throws FindEntityException {
		try {
			return this.em.find(Location.class, lid);
		} catch (final Exception e) {
			throw new FindEntityException(Location.class, lid);
		}
	}
}
