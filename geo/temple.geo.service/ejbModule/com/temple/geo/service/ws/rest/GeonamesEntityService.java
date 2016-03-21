package com.temple.geo.service.ws.rest;

import java.io.StringReader;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.temple.geo.service.FilterData;
import com.temple.geo.service.GeoEntityManager;
import com.temple.service.ServiceException;
import com.temple.service.cdi.TempleObject;
import com.temple.service.ws.AbstractWebServiceBean;
import com.temple.util.geo.Country;
import com.temple.util.json.JsonUtil;

/**
 * TODOC
 */
@Path("/geo")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class GeonamesEntityService extends AbstractWebServiceBean {

	private static final long serialVersionUID = 1L;

	@Inject
	@TempleObject
	private GeoEntityManager m;

	@GET
	@Path("divisions/{country: [A-Z][a-zA-Z_]+}")
	public JsonArray findCountryDivisions(@PathParam("country") Country c) throws ServiceException {
		return JsonUtil.toJsonArray(this.m.findCountryDivisions(c));
	}

	@GET
	@Path("divisions/{parentId: [1-9][0-9]+}")
	public JsonArray findCountryDivisions(@PathParam("parentId") int parentId) throws ServiceException {
		return JsonUtil.toJsonArray(this.m.findCountryDivisions(parentId));
	}

	@GET
	@Path("cities/count/{country: [A-Z][a-zA-Z_]+}")
	public JsonStructure countHumanSettlements(@PathParam("country") Country c) throws ServiceException {
		return Json.createArrayBuilder().add(this.m.countHumanSettlements(c)).build();
	}

	@GET
	@Path("cities/count/{parentId: [1-9][0-9]+}")
	public JsonStructure countHumanSettlements(@PathParam("parentId") int parentId) throws ServiceException {
		return Json.createArrayBuilder().add(this.m.countHumanSettlements(parentId)).build();
	}

	@GET
	@Path("cities")
	public JsonArray findHumanSettlements(@DefaultValue("{}") @QueryParam("filter") String filter)
			throws ServiceException {
		try (final JsonReader r = Json.createReader(new StringReader(filter))) {
			final FilterData fd = new FilterData();
			fd.setValues(r.readObject());
			return JsonUtil.toJsonArray(this.m.findHumanSettlements(fd));
		}
	}

}
