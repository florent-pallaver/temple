package com.temple.geo.service.ws.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.temple.util.AbstractLogger;
import com.temple.util.TempleUtil;
import com.temple.util.geo.Country;

@RunWith(Parameterized.class)
public class GeonamesDivisionServiceTest extends AbstractLogger {

	private static final MediaType[] types = { MediaType.APPLICATION_JSON_TYPE, MediaType.APPLICATION_XML_TYPE };

	@Parameters
	public static Collection<Object[]> data() {
		final Country[] values = Country.values();
		final Collection<Object[]> data = new ArrayList<>();
		for (int i = 10; i-- > 0;) {
			data.add(new Object[] { TempleUtil
					.random(values), /*
										 * TempleUtil.random(
										 * GeonamesEntityServiceTest.types)
										 */ });
		}
		data.add(new Object[] {
				Country.France, /* MediaType.APPLICATION_JSON_TYPE */ });
		data.add(new Object[] {
				Country.United_States, /* MediaType.APPLICATION_XML_TYPE */ });
		return data;
	}

	private Client client;

	private WebTarget target;

	@Parameter
	public Country country;

	// @Parameter(1)
	public MediaType mediaType;

	@Before
	public void init() {
		this.client = ClientBuilder.newClient();
		this.target = this.client.target("http://localhost:8080/temple.geo.web/webapi");
		this.mediaType = MediaType.APPLICATION_JSON_TYPE;
	}

	@Test
	public void testCitiesCount() {
		List<?> list;
		this.info("Testing country : %s with %s", this.country, this.mediaType);

		// count cities
		this.testCount(this.country.name());

		list = this.target.path("cities").queryParam("filter", "{\"country\":\"" + this.country.name() + "\"}").request(this.mediaType).get(JsonArray.class);
		Assert.assertFalse(list.isEmpty());
		this.info("%s cities", list.size());
	}

	// @Test
	public void testCities() {
		List<?> list;
		this.info("Testing country : %s with %s", this.country, this.mediaType);

		// count cities
		this.testCount(this.country.name());

		// divisions
		list = this.target.path("divisions/" + this.country.name()).request(this.mediaType).get(JsonArray.class);

		Assume.assumeFalse("No divisions in this country ...", list.isEmpty());

		this.info("%s divisions", list.size());

		final Optional<?> anyDiv = list.stream().findAny();
		Assert.assertTrue(anyDiv.isPresent());

		final JsonObject div = (JsonObject) anyDiv.get();
		this.info("Testing div : %s", div.getString("name"));

		// count cities in division
		this.testCount(div.getInt("id"));

		//
		//
		// list = this.target.path("cities/" +
		// div.getInt("id")).request(this.mediaType).get(JsonArray.class);
		// Assert.assertFalse(list.isEmpty());
		// this.info("%s cities", list.size());
	}

	private void testCount(Object s) {
		final JsonArray list = this.target.path("cities/count/" + s).request(this.mediaType).get(JsonArray.class);

		Assert.assertTrue(list.size() == 1);

		final JsonNumber x = (JsonNumber) list.get(0);

		Assume.assumeTrue(x.intValue() > 0);
	}

}
