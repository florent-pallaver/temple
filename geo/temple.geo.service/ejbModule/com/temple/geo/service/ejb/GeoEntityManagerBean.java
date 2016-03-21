package com.temple.geo.service.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.temple.geo.model.CountryDivisionEntity;
import com.temple.geo.model.HumanSettlementEntity;
import com.temple.geo.service.FilterData;
import com.temple.geo.service.GeoEntityManager;
import com.temple.service.ServiceException;
import com.temple.service.cdi.TempleObject;
import com.temple.service.ejb.AbstractEJBBean;
import com.temple.util.geo.Country;

/**
 * Session Bean implementation class GeoEntityManagerBean
 */
@Stateless(name = GeoEntityManager.BEAN_NAME)
public class GeoEntityManagerBean extends AbstractEJBBean implements GeoEntityManager {

	private static final long serialVersionUID = 1L;

	@Inject
	@TempleObject
	private GeoEntityManager m;

	@Override
	public List<? extends CountryDivisionEntity> findCountryDivisions(Country c) throws ServiceException {
		return this.m.findCountryDivisions(c);
	}

	@Override
	public List<? extends CountryDivisionEntity> findCountryDivisions(int parentId) throws ServiceException {
		return this.m.findCountryDivisions(parentId);
	}

	@Override
	public int countHumanSettlements(Country c) throws ServiceException {
		return this.m.countHumanSettlements(c);
	}

	@Override
	public int countHumanSettlements(int parentId) throws ServiceException {
		return this.m.countHumanSettlements(parentId);
	}

	@Override
	public List<? extends HumanSettlementEntity> findHumanSettlements(FilterData filter) throws ServiceException {
		return this.m.findHumanSettlements(filter);
	}

}
