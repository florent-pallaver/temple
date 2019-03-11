package com.temple.geo.service.cdi.request;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.temple.geo.model.HumanSettlementEntity;
import com.temple.geo.model.geonames.AbstractCountryArea;
import com.temple.geo.model.geonames.AdministrativeDivision;
import com.temple.geo.model.geonames.filter.CountryAreaFilter;
import com.temple.geo.service.GeoEntityManager;
import com.temple.model.filter.EntityFilter;
import com.temple.service.ServiceException;
import com.temple.service.cdi.AbstractCDIBean;
import com.temple.service.cdi.TempleObject;
import com.temple.util.geo.Country;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
@RequestScoped
@TempleObject
public class GeonamesEntityManagerBean extends AbstractCDIBean implements GeoEntityManager {

	private static final long serialVersionUID = 1L;

	@Inject
	@TempleObject
	private EntityManager em;

	@Override
	public List<AdministrativeDivision> findCountryDivisions(Country c) throws ServiceException {
		final CountryAreaFilter<AdministrativeDivision> adf = CountryAreaFilter.adminDivFilter();
		adf.setCountry(c);
		return this.findAll(adf);
	}

	@Override
	public List<AdministrativeDivision> findCountryDivisions(int parentId) throws ServiceException {
		final CountryAreaFilter<AdministrativeDivision> adf = CountryAreaFilter.adminDivFilter();
		adf.setParentId(parentId);
		return this.findAll(adf);
	}

	@Override
	public int countHumanSettlements(Country c) throws ServiceException {
		return CountryAreaFilter.populatedPlaceFilter().setCountry(c).createCountQuery(this.em).getSingleResult().intValue();
	}

	@Override
	public int countHumanSettlements(int parentId) throws ServiceException {
		return CountryAreaFilter.populatedPlaceFilter().setParentId(parentId).createCountQuery(this.em).getSingleResult().intValue();
	}

	@Override
	public <R extends Serializable> List<R> findHumanSettlements(EntityFilter<? extends HumanSettlementEntity, R> filter) throws ServiceException {
		return filter.createTypedQuery(this.em).getResultList();
	}

	private <GE extends AbstractCountryArea> List<GE> findAll(CountryAreaFilter<GE> filter) {
		return filter.createTypedQuery(this.em).getResultList();
	}

}
