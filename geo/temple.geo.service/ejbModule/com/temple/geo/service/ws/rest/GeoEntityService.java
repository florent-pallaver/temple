package com.temple.geo.service.ws.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Path;

import com.temple.geo.model.CountryDivisionEntity;
import com.temple.geo.model.HumanSettlementEntity;
import com.temple.geo.service.GeoEntityManager;
import com.temple.service.ServiceException;
import com.temple.service.cdi.TempleObject;
import com.temple.service.ws.AbstractWebServiceBean;
import com.temple.util.geo.Country;

/**
 * Session Bean implementation class GeoEntityManagerBean
 */
@Path("/geo")
public class GeoEntityService extends AbstractWebServiceBean implements GeoEntityManager {

	private static final long serialVersionUID = 1L;

	@Inject
	@TempleObject
	private GeoEntityManager m ;

	@Override
	public List<? extends CountryDivisionEntity> findCountryDivisions(Country c) throws ServiceException {
		return this.m.findCountryDivisions(c);
	}

	@Override
	public List<? extends CountryDivisionEntity> findCountryDivisions(int parentId) throws ServiceException {
		return this.m.findCountryDivisions(parentId);
	}

	@Override
	public List<? extends HumanSettlementEntity> findHumanSettlements(Country c) throws ServiceException {
		return this.m.findHumanSettlements(c);
	}

	@Override
	public List<? extends HumanSettlementEntity> findHumanSettlements(int parentId) throws ServiceException {
		return this.m.findHumanSettlements(parentId);
	}

}
