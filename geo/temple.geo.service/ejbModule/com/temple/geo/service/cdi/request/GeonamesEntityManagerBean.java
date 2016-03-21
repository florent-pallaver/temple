package com.temple.geo.service.cdi.request;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.temple.geo.model.geonames.AbstractCountryArea;
import com.temple.geo.model.geonames.AdministrativeDivision;
import com.temple.geo.model.geonames.Feature;
import com.temple.geo.model.geonames.PopulatedPlace;
import com.temple.geo.model.geonames.filter.AbstractCountryAreaFilter;
import com.temple.geo.model.geonames.filter.CountryAreaFilter;
import com.temple.geo.service.FilterData;
import com.temple.geo.service.GeoEntityManager;
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
		adf.setFeatures(Feature.ADM1);
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
		return CountryAreaFilter.populatedPlaceFilter().setCountry(c).setFeatures(Feature.HUMAN_SETTLEMENTS)
				.createCountQuery(this.em).getSingleResult().intValue();
	}

	@Override
	public int countHumanSettlements(int parentId) throws ServiceException {
		return CountryAreaFilter.populatedPlaceFilter().setParentId(parentId).setFeatures(Feature.HUMAN_SETTLEMENTS)
				.createCountQuery(this.em).getSingleResult().intValue();
	}

	@Override
	public List<PopulatedPlace> findHumanSettlements(FilterData filter) throws ServiceException {
		final AbstractCountryAreaFilter<PopulatedPlace, PopulatedPlace> caf = CountryAreaFilter.populatedPlaceFilter()
				.setCountry(filter.getCountry()).setParentId(filter.getParentId())
				.setFeatures(Feature.HUMAN_SETTLEMENTS);

		// sets the page per count first cause it resets the page
		caf.setPerPageCount((short) filter.getCount());
		caf.setPage(filter.getPage());

		return caf.createTypedQuery(this.em).getResultList();
	}

	private <GE extends AbstractCountryArea> List<GE> findAll(CountryAreaFilter<GE> filter) {
		return filter.createTypedQuery(this.em).getResultList();
	}

}
