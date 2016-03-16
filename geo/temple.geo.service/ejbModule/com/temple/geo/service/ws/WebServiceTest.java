package com.temple.geo.service.ws;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Variant;

import com.temple.geo.model.CountryDivisionEntity;
import com.temple.geo.model.GeoEntity;
import com.temple.geo.model.Place;
import com.temple.geo.model.geonames.AbstractCountryArea_;
import com.temple.geo.model.geonames.AdministrativeDivision;
import com.temple.geo.model.geonames.Feature;
import com.temple.geo.model.geonames.PopulatedPlace;
import com.temple.geo.service.GeoEntityManager;
import com.temple.service.ServiceException;
import com.temple.service.cdi.TempleObject;
import com.temple.service.ws.AbstractWebServiceBean;
import com.temple.util.Charsets;
import com.temple.util.Nameable;
import com.temple.util.geo.Country;

@Path("/service")
// @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@RequestScoped
public class WebServiceTest extends AbstractWebServiceBean {

	private static final long serialVersionUID = 1L;

	private static final List<Variant> variants;

	static {
		variants = Collections.singletonList(
				Variant.encodings(Charsets.utf8.name()).mediaTypes(MediaType.APPLICATION_JSON_TYPE).build().get(0));
	}

	@Inject
	@TempleObject
	private GeoEntityManager m;

	@Inject
	@TempleObject
	private EntityManager em;

	@Context
	private Request req;

	@GET
	@Path("divs/{c}")
	public List<Place> fuck(@PathParam("c") Country c) {
		final CriteriaBuilder cb = this.em.getCriteriaBuilder();
		final CriteriaQuery<Place> tq = cb.createQuery(Place.class);
		final Root<AdministrativeDivision> root = tq.from(AdministrativeDivision.class);
		tq.select(cb.construct(Place.class, root.get(AbstractCountryArea_.id), root.get(AbstractCountryArea_.name)))
				.where(cb.equal(root.get(AbstractCountryArea_.country), c),
						root.get(AbstractCountryArea_.feature).in(Feature.ADM1));
		return this.em.createQuery(tq).getResultList();
	}

	@GET
	@Path("places/{d}")
	public List<Place> fùck(@PathParam("d") int d) {
		final CriteriaBuilder cb = this.em.getCriteriaBuilder();
		final CriteriaQuery<Place> tq = cb.createQuery(Place.class);
		final Root<PopulatedPlace> root = tq.from(PopulatedPlace.class);
		tq.select(cb.construct(Place.class, root.get(AbstractCountryArea_.id), root.get(AbstractCountryArea_.name)))
				.where(cb.equal(root.get(AbstractCountryArea_.parentId), d));
		return this.em.createQuery(tq).getResultList();
	}

	@GET
	@Path("test")
	// @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Nameable> test() {
		this.info("test ...");
		int i = 5;
		return Arrays.asList(new Place(i++, "Sample"), new Place(i++, "Sample"), new Place(i++, "Sample"));
	}

	@GET
	@Path("divisions/{c}")
	public List<CountryDivisionEntity> divisions(@PathParam("c") Country c) throws ServiceException {

		// final Variant selected =
		// this.req.selectVariant(WebServiceTest.variants);
		// final ResponseBuilder b ;
		// if(selected == null) {
		// b = Response.notAcceptable(WebServiceTest.variants);
		// } else {
		// b = Response.ok(
		// return this.toJsonArray(this.m.findCountryDivisions(c)) ;
		// , selected) ;
		// }
		return new ArrayList<>(this.m.findCountryDivisions(c));
		// return b.build();
	}

	// @GET
	// @Path("places/{d}")
	// public JsonStructure places(@PathParam("d") int parentId) throws
	// ServiceException {
	// return this.toJsonArray(this.m.findHumanSettlements(parentId));
	// }

	private JsonArray toJsonArray(Collection<? extends GeoEntity> c) {
		final JsonArrayBuilder jab = Json.createArrayBuilder();
		for (final GeoEntity e : c) {
			final JsonArrayBuilder eJab = Json.createArrayBuilder();
			eJab.add((Integer) e.getId());
			eJab.add(e.getName());
			jab.add(eJab);
		}
		return jab.build();
	}

}