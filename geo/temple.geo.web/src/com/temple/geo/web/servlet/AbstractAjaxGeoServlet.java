package com.temple.geo.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.temple.geo.model.GeoEntity;
import com.temple.geo.service.GeoEntityManager;
import com.temple.service.ServiceException;
import com.temple.service.cdi.TempleObject;
import com.temple.util.geo.Country;
import com.temple.web.servlet.AjaxRequestServlet;
import com.temple.web.servlet.InvalidRequestException;
import com.temple.web.servlet.JsonResponse;

/**
 * TODOC
 * @author flominou
 * @param <GE>
 */
public abstract class AbstractAjaxGeoServlet<GE extends GeoEntity> extends AjaxRequestServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * TODOC
	 */
	public static final String COUNTRY_KEY = "c" ;

	/**
	 * TODOC
	 */
	public static final String PARENT_KEY = "p" ;

	private static final Country[] all = Country.values() ;

	@Inject
	@TempleObject
	protected GeoEntityManager gdm ;

	@Override
	protected JsonResponse createResponse(HttpServletRequest request) throws ServiceException, ServletException, IOException {
		final List<? extends GE> ads ;
		final Integer p = this.getIntegerParam(request, AbstractAjaxGeoServlet.PARENT_KEY) ;
		if(p == null) {
			final Integer c = this.getIntegerParam(request, AbstractAjaxGeoServlet.COUNTRY_KEY) ;
			if(c == null || c < 0 || c >= AbstractAjaxGeoServlet.all.length) {
				throw new InvalidRequestException() ;
			}
			ads = this.findGeoEntities(AbstractAjaxGeoServlet.all[c]) ;
		} else {
			ads = this.findGeoEntities(p) ;
		}
		final JsonResponse jr = new JsonResponse(true, null) ;
		ads.forEach(ad -> jr.addData(ad.getId(), ad.getName()));
		return jr ;
	}

	/**
	 * TODOC
	 * @param c
	 * @return
	 * @throws ServiceException
	 */
	protected abstract List<? extends GE> findGeoEntities(Country c) throws ServiceException ;

	/**
	 * TODOC
	 * @param parentId
	 * @return
	 * @throws ServiceException
	 */
	protected abstract List<? extends GE> findGeoEntities(Integer parentId) throws ServiceException ;

}
