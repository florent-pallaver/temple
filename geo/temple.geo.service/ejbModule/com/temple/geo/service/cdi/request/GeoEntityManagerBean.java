package com.temple.geo.service.cdi.request;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.temple.geo.model.CountryDivisionEntity;
import com.temple.geo.model.HumanSettlementEntity;
import com.temple.geo.model.Place;
import com.temple.geo.model.geonames.AbstractCountryArea;
import com.temple.geo.model.geonames.AbstractCountryArea_;
import com.temple.geo.model.geonames.AdministrativeDivision;
import com.temple.geo.model.geonames.AdministrativeDivision_;
import com.temple.geo.model.geonames.Feature;
import com.temple.geo.model.geonames.PopulatedPlace;
import com.temple.geo.model.geonames.filter.CountryAreaFilter;
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
public class GeoEntityManagerBean extends AbstractCDIBean implements GeoEntityManager {

	private static final long serialVersionUID = 1L;

	@Inject
	@TempleObject
	private EntityManager em;

	@Override
	public List<? extends CountryDivisionEntity> findCountryDivisions(Country c) throws ServiceException {
		final CountryAreaFilter<AdministrativeDivision> adf = CountryAreaFilter.adminDivFilter();
		adf.setCountry(c);
		adf.setFeatures(Feature.ADM1);
		return this.findAll(adf);
	}

	@Override
	public List<? extends CountryDivisionEntity> findCountryDivisions(int parentId) throws ServiceException {
		final CountryAreaFilter<AdministrativeDivision> adf = CountryAreaFilter.adminDivFilter();
		adf.setParentId(parentId);
		return this.findAll(adf);
	}

	@Override
	public List<? extends HumanSettlementEntity> findHumanSettlements(Country c) throws ServiceException {
		final CountryAreaFilter<PopulatedPlace> cdf = CountryAreaFilter.populatedPlaceFilter();
		cdf.setCountry(c);
		return this.findAll(cdf);
	}

	@Override
	public List<? extends HumanSettlementEntity> findHumanSettlements(int parentId) throws ServiceException {
		final CountryAreaFilter<PopulatedPlace> cdf = CountryAreaFilter.populatedPlaceFilter();
		cdf.setParentId(parentId);
		return this.findAll(cdf);
	}

	private <GE extends AbstractCountryArea> List<? extends GE> findAll(CountryAreaFilter<GE> filter) {
		return filter.createTypedQuery(this.em).getResultList();
	}

}
