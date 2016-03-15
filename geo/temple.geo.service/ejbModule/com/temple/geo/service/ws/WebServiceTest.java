package com.temple.geo.service.ws;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonStructure;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Variant;

import com.temple.geo.model.GeoEntity;
import com.temple.geo.service.GeoEntityManager;
import com.temple.service.ServiceException;
import com.temple.service.cdi.TempleObject;
import com.temple.service.ws.AbstractWebServiceBean;
import com.temple.util.Charsets;
import com.temple.util.geo.Country;

@Path("/service")
//@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@RequestScoped
public class WebServiceTest extends AbstractWebServiceBean {

	private static final long serialVersionUID = 1L;

	private static final List<Variant> variants ;

	static {
		variants = 	Collections.singletonList(Variant.encodings(Charsets.utf8.name()).mediaTypes(MediaType.APPLICATION_JSON_TYPE)
				.build().get(0)) ;
	}

	@Inject
	@TempleObject
	private GeoEntityManager m;

	@Context
	private Request req ;

	@GET
	@Path("divisions/{c}")
	public Response divisions(@PathParam("c") Country c) throws ServiceException {

		final Variant selected = this.req.selectVariant(WebServiceTest.variants);
		final ResponseBuilder b ;
		if(selected == null) {
			b = Response.notAcceptable(WebServiceTest.variants);
		} else {
			b = Response.ok(this.toJsonArray(this.m.findCountryDivisions(c)), selected) ;
		}

		return b.build();
	}

	@GET
	@Path("places/{d}")
	public JsonStructure places(@PathParam("d") int parentId) throws ServiceException {
		return this.toJsonArray(this.m.findHumanSettlements(parentId));
	}

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