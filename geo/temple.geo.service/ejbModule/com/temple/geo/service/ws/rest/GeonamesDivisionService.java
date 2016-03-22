package com.temple.geo.service.ws.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.validation.constraints.Min;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.temple.geo.model.geonames.PopulatedPlace;
import com.temple.geo.model.geonames.filter.AbstractCountryAreaFilter;
import com.temple.geo.model.geonames.filter.CountryAreaFilter;
import com.temple.geo.service.GeoEntityManager;
import com.temple.service.ServiceException;
import com.temple.service.cdi.TempleObject;
import com.temple.service.ws.AbstractWebServiceBean;
import com.temple.util.json.JsonUtil;

/**
 * TODOC
 */
@Path("{parentId: [1-9][0-9]*}")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class GeonamesDivisionService extends AbstractWebServiceBean {

	private static final long serialVersionUID = 1L;

	@Inject
	@TempleObject
	private GeoEntityManager m;

	@PathParam("parentId")
	private Integer parentId;

	@GET
	@Path("cities/count")
	public JsonArray citiesCount(@PathParam("parentId") int parentId) throws ServiceException {
		return Json.createArrayBuilder().add(this.m.countHumanSettlements(parentId)).build();
	}

	@GET
	@Path("cities")
	public JsonArray cities(@Min(1) @DefaultValue("1") @QueryParam("page") int page, @Min(0) @DefaultValue("0") @QueryParam("count") short count)
			throws ServiceException {
		final AbstractCountryAreaFilter<PopulatedPlace, PopulatedPlace> filter = CountryAreaFilter.populatedPlaceFilter().setParentId(this.parentId);

		filter.setPerPageCount(count);
		filter.setPage(page);

		return JsonUtil.toJsonArray(this.m.findHumanSettlements(filter));
	}

}
