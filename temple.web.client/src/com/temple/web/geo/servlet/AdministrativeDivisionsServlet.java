package com.temple.web.geo.servlet;

import com.temple.model.geo.CountryDivisionEntity;
import com.temple.service.ServiceException;
import com.temple.util.geo.Country;
import java.util.List;

/**
 * TODOC
 * @author flominou
 */
public class AdministrativeDivisionsServlet extends AbstractAjaxGeoServlet<CountryDivisionEntity> {

	@Override
	protected List<? extends CountryDivisionEntity> findGeoEntities(Country c) throws ServiceException {
		return this.gdm.findCountryDivisions(c) ;
	}

	@Override
	protected List<? extends CountryDivisionEntity> findGeoEntities(Integer parentId) throws ServiceException {
		return this.gdm.findCountryDivisions(parentId) ;
	}
	
}
