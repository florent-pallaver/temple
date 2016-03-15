package com.temple.geo.web.servlet;

import java.util.List;

import com.temple.geo.model.HumanSettlementEntity;
import com.temple.service.ServiceException;
import com.temple.util.geo.Country;
import com.temple.web.servlet.InvalidRequestException;

/**
 * TODOC
 * @author flominou
 */
public class PopulatedPlacesServlet extends AbstractAjaxGeoServlet<HumanSettlementEntity> {

	private static final long serialVersionUID = 1L;

	@Override
	protected List<? extends HumanSettlementEntity> findGeoEntities(Country c) throws ServiceException {
		throw new InvalidRequestException() ;
		//		return this.gdm.findHumanSettlements(c) ;
	}

	@Override
	protected List<? extends HumanSettlementEntity> findGeoEntities(Integer parentId) throws ServiceException {
		return this.gdm.findHumanSettlements(parentId) ;
	}

}
