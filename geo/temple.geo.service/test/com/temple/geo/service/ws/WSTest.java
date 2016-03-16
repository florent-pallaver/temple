package com.temple.geo.service.ws;

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

import com.temple.geo.model.Place;
import com.temple.util.AbstractLogger;
import com.temple.util.TempleUtil;
import com.temple.util.geo.Country;

public class WSTest extends AbstractLogger {

	private final MediaType[] types = { MediaType.APPLICATION_JSON_TYPE, MediaType.APPLICATION_XML_TYPE };

	private GenericType<List<Place>> genericType;

	private Client client;

	private WebTarget target;

	@Before
	public void init() {
		this.genericType = new GenericType<List<Place>>() {
		};
		this.client = ClientBuilder.newClient();
		this.target = this.client.target("http://localhost:8080/temple.geo.web/api/service");
	}

	@Test
	public void testRandomCountry() {
		List<Place> list;
		final Country country = TempleUtil.random(Country.values());
		this.info("Testing country : %s", country);
		for (final MediaType mediaType : this.types) {
			this.info("Requesting %s", mediaType);

			list = this.target.path("divs/" + country.name()).request(mediaType).get(this.genericType);
			Assert.assertFalse(list.isEmpty());
			this.info("%s divisions", list.size());

			final Optional<Place> anyDiv = list.stream().findAny();
			Assert.assertTrue(anyDiv.isPresent());

			final Place div = anyDiv.get();
			this.info("Testing div : %s", div.getName());
			list = this.target.path("places/" + div.getId()).request(mediaType).get(this.genericType);
			Assert.assertFalse(list.isEmpty());
			this.info("%s places", list.size());
		}
	}

}
