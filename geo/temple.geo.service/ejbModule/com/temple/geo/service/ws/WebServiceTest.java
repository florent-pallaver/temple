package com.temple.geo.service.ws;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;

import com.temple.geo.model.geonames.Feature;
import com.temple.geo.model.geonames.PopulatedPlace;
import com.temple.geo.model.geonames.filter.AbstractCountryAreaFilter;
import com.temple.geo.model.geonames.filter.CountryAreaFilter;
import com.temple.geo.model.geonames.filter.SimpleCountryAreaFilter;
import com.temple.geo.service.GeoEntityManager;
import com.temple.model.filter.NamedEntity;
import com.temple.service.cdi.TempleObject;
import com.temple.service.ws.AbstractWebServiceBean;
import com.temple.util.geo.Country;

@Path("/service")
// @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@RequestScoped
public class WebServiceTest extends AbstractWebServiceBean {

	private static final long serialVersionUID = 1L;

	@Inject
	@TempleObject
	private GeoEntityManager m;

	@Inject
	@TempleObject
	private EntityManager em;

	@Context
	private Request req;

	@GET
	@Path("countries")
	public Country[] countries() {
		return Country.values();
	}

	@GET
	@Path("divisions/{c}")
	public NamedEntity[] fuck(@PathParam("c") Country c) {
		final List<NamedEntity> list = SimpleCountryAreaFilter.adminDivFilter().setCountry(c).setFeatures(Feature.ADM1)
				.createTypedQuery(this.em).getResultList();
		return list.toArray(new NamedEntity[list.size()]);
	}

	@GET
	@Path("cities/{d}")
	public List<NamedEntity> fûck(@PathParam("d") int d, @DefaultValue("1") @QueryParam("page") int page) {
		final AbstractCountryAreaFilter<PopulatedPlace, NamedEntity> filter = SimpleCountryAreaFilter
				.populatedPlaceFilter().setParentId(d).setFeatures(Feature.HUMAN_SETTLEMENTS);
		filter.setPage(page);
		return filter.createTypedQuery(this.em).getResultList();
	}

	@GET
	@Path("places/{d}")
	public List<PopulatedPlace> fµck(@PathParam("d") int d, @DefaultValue("1") @QueryParam("page") int page) {
		final AbstractCountryAreaFilter<PopulatedPlace, PopulatedPlace> ppFilter = CountryAreaFilter
				.populatedPlaceFilter().setParentId(d).setFeatures(Feature.HUMAN_SETTLEMENTS);
		ppFilter.setPage(page);
		return ppFilter.createTypedQuery(this.em).getResultList();
	}

	@GET
	@Path("city/{id}")
	public PopulatedPlace fùck(@PathParam("id") int id) {
		return this.em.find(PopulatedPlace.class, id);
	}

}