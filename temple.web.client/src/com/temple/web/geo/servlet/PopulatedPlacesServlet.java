package com.temple.web.geo.servlet;

import com.temple.model.geo.HumanSettlementEntity;
import com.temple.service.ServiceException;
import com.temple.util.geo.Country;
import java.util.List;

/**
 * TODOC
 * @author flominou
 */
public class PopulatedPlacesServlet extends AbstractAjaxGeoServlet<HumanSettlementEntity> {

	@Override
	protected List<? extends HumanSettlementEntity> findGeoEntities(Country c) throws ServiceException {
		return this.gdm.findHumanSettlements(c) ;
	}

	@Override
	protected List<? extends HumanSettlementEntity> findGeoEntities(Integer parentId) throws ServiceException {
		return this.gdm.findHumanSettlements(parentId) ;
	}

}
