package com.temple.geo.web.servlet;

import java.util.List;

import com.temple.geo.model.CountryDivisionEntity;
import com.temple.service.ServiceException;
import com.temple.util.geo.Country;

/**
 * TODOC
 * @author flominou
 */
public class AdministrativeDivisionsServlet extends AbstractAjaxGeoServlet<CountryDivisionEntity> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected List<? extends CountryDivisionEntity> findGeoEntities(Country c) throws ServiceException {
		return this.gdm.findCountryDivisions(c) ;
	}

	@Override
	protected List<? extends CountryDivisionEntity> findGeoEntities(Integer parentId) throws ServiceException {
		return this.gdm.findCountryDivisions(parentId) ;
	}

}
