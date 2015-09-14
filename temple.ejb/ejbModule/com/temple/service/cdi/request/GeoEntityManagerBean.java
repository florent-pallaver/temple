package com.temple.service.cdi.request;

import com.temple.model.geo.CountryDivisionEntity;
import com.temple.model.geo.HumanSettlementEntity;
import com.temple.model.geo.geonames.AdministrativeDivision;
import com.temple.model.geo.geonames.filter.CountryAreaFilter;
import com.temple.model.geo.geonames.Feature;
import com.temple.model.geo.geonames.PopulatedPlace;
import com.temple.service.ServiceException;
import com.temple.service.cdi.AbstractCDIBean;
import com.temple.service.cdi.TempleObject;
import com.temple.service.model.ModelManager;
import com.temple.service.model.geo.GeoEntityManager;
import com.temple.util.geo.Country;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

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
	private ModelManager mm ;
	
	@Override
	public List<? extends CountryDivisionEntity> findCountryDivisions(Country c) throws ServiceException {
		final CountryAreaFilter<AdministrativeDivision> adf = new CountryAreaFilter(AdministrativeDivision.class);
		adf.setCountry(c);
		adf.setFeatures(Feature.ADM1);
		return this.mm.find(adf)  ;
	}

	@Override
	public List<? extends CountryDivisionEntity> findCountryDivisions(int parentId) throws ServiceException {
		final CountryAreaFilter<AdministrativeDivision> adf = new CountryAreaFilter(AdministrativeDivision.class);
		adf.setParentId(parentId);
		return this.mm.find(adf) ;
	}

	@Override
	public List<? extends HumanSettlementEntity> findHumanSettlements(Country c) throws ServiceException {
		final CountryAreaFilter<PopulatedPlace> cdf = new CountryAreaFilter(PopulatedPlace.class);
		cdf.setCountry(c);
		return this.mm.find(cdf) ;
		
	}

	@Override
	public List<? extends HumanSettlementEntity> findHumanSettlements(int parentId) throws ServiceException {
		final CountryAreaFilter<PopulatedPlace> cdf = new CountryAreaFilter(PopulatedPlace.class);
		cdf.setParentId(parentId);
		return this.mm.find(cdf) ;
	}

}
