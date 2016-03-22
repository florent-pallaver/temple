package com.temple.geo.service.ws.rest;

import javax.annotation.PostConstruct;
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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import com.temple.geo.model.geonames.PopulatedPlace;
import com.temple.geo.model.geonames.filter.AbstractCountryAreaFilter;
import com.temple.geo.model.geonames.filter.CountryAreaFilter;
import com.temple.geo.service.GeoEntityManager;
import com.temple.service.ServiceException;
import com.temple.service.cdi.TempleObject;
import com.temple.service.ws.AbstractWebServiceBean;
import com.temple.util.geo.Country;
import com.temple.util.json.JsonUtil;

/**
 * TODOC
 */
@Path("{isoCode: [a-zA-Z][a-zA-Z]}")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class GeonamesCountryService extends AbstractWebServiceBean {

	private static final long serialVersionUID = 1L;

	@Inject
	@TempleObject
	private GeoEntityManager m;

	@PathParam("isoCode")
	private String isoCode;

	private Country country;

	@Override
	@PostConstruct
	protected void postConstruct() {
		super.postConstruct();
		try {
			this.country = Country.getByISOCode(this.isoCode);
		} catch (final IllegalArgumentException e) {
			throw new WebApplicationException(e.getMessage(), 400);
		}
	}

	@GET
	@Path("divisions")
	public JsonArray divisions() throws ServiceException {
		return JsonUtil.toJsonArray(this.m.findCountryDivisions(this.country));
	}

	@GET
	@Path("cities/count")
	public JsonArray citiesCount() throws ServiceException {
		return Json.createArrayBuilder().add(this.m.countHumanSettlements(this.country)).build();
	}

	@GET
	@Path("cities")
	public JsonArray cities(@Min(1) @DefaultValue("1") @QueryParam("page") int page, @Min(0) @DefaultValue("0") @QueryParam("count") short count)
			throws ServiceException {
		final AbstractCountryAreaFilter<PopulatedPlace, PopulatedPlace> filter = CountryAreaFilter.populatedPlaceFilter().setCountry(this.country);

		filter.setPerPageCount(count);
		filter.setPage(page);

		return JsonUtil.toJsonArray(this.m.findHumanSettlements(filter));
	}

}
