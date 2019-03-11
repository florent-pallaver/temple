package com.temple.geo.service.ws.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.temple.model.filter.NamedEntity;
import com.temple.util.AbstractLogger;
import com.temple.util.TempleUtil;
import com.temple.util.geo.Country;

@RunWith(Parameterized.class)
public class GeonamesCountryServiceTest extends AbstractLogger {

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

	private GenericType<List<NamedEntity>> genericType;

	private Client client;

	private WebTarget target;

	@Parameter
	public Country country;

	// @Parameter(1)
	public MediaType mediaType;

	@Before
	public void init() {
		this.genericType = new GenericType<List<NamedEntity>>() {
		};
		this.client = ClientBuilder.newClient();
		this.target = this.client.target("http://localhost:8080/temple.geo.web/webapi/" + this.country.getIsoCode());
		this.mediaType = MediaType.APPLICATION_JSON_TYPE;
	}

	@Test
	public void test() {
		JsonArray list = this.target.path("cities/count").request(this.mediaType).get(JsonArray.class);

		Assert.assertEquals(list.size(), 1);

		final int totalCount = ((JsonNumber) list.get(0)).intValue();

		Assume.assumeTrue(this.country.name(), totalCount > 0);

		list = this.target.path("divisions").request(this.mediaType).get(JsonArray.class);

		Assume.assumeFalse("No divisions in this country ...", list.isEmpty());

		int sum = 0;

		for (final JsonValue jsonValue : list) {
			final JsonObject jo = (JsonObject) jsonValue;
			if (jo.containsKey("placesCount")) {
				sum += jo.getInt("placesCount");
			} else {
				System.out.println(jo.toString());
			}
		}

		Assert.assertEquals(sum, totalCount);

		final int randomCount = TempleUtil.random(1, 100);
		this.testPagination(randomCount, totalCount);

	}

	private void testPagination(int wantedCount, int total) {
		final JsonArray list = this.target.path("cities").queryParam("count", wantedCount).request(this.mediaType).get(JsonArray.class);
		Assert.assertEquals(list.size(), Math.min(total, wantedCount));
	}

}
