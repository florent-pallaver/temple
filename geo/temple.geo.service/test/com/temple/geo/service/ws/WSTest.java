package com.temple.geo.service.ws;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.junit.Assert;
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
public class WSTest extends AbstractLogger {

	private static final MediaType[] types = { MediaType.APPLICATION_JSON_TYPE, MediaType.APPLICATION_XML_TYPE };

	@Parameters
	public static Collection<Object[]> data() {
		final Country[] values = Country.values();
		final Collection<Object[]> data = new ArrayList<>();
		for (int i = 10; i-- > 0;) {
			data.add(new Object[] { TempleUtil.random(values), TempleUtil.random(WSTest.types) });
		}
		data.add(new Object[] { Country.France, MediaType.APPLICATION_JSON_TYPE });
		data.add(new Object[] { Country.United_States, MediaType.APPLICATION_XML_TYPE });
		return data;
	}

	private GenericType<List<NamedEntity>> genericType;

	private Client client;

	private WebTarget target;

	@Parameter
	public Country country;

	@Parameter(1)
	public MediaType mediaType;

	@Before
	public void init() {
		this.genericType = new GenericType<List<NamedEntity>>() {
		};
		this.client = ClientBuilder.newClient();
		this.target = this.client.target("http://localhost:8080/temple.geo.web/webapi/service");
	}

	@Test
	public void testCountries() {
		final Country[] countries = this.target.path("countries").request().get(Country[].class);
		Assert.assertArrayEquals(Country.values(), countries);
	}

	@Test
	public void testRandomCountry() {
		List<NamedEntity> list;
		this.info("Testing country : %s with %s", this.country, this.mediaType);

		list = this.target.path("divisions/" + this.country.name()).request(this.mediaType).get(this.genericType);
		if (!list.isEmpty()) {
			this.info("%s divisions", list.size());

			final Optional<NamedEntity> anyDiv = list.stream().findAny();
			Assert.assertTrue(anyDiv.isPresent());

			final NamedEntity div = anyDiv.get();
			this.info("Testing div : %s", div.getName());
			list = this.target.path("cities/" + div.getId()).request(this.mediaType).get(this.genericType);
			Assert.assertFalse(list.isEmpty());
			this.info("%s cities", list.size());
		}
	}

}
