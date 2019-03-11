package com.temple.util.geo;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.Test;

import com.temple.util.geo.Country;

public class CountryTest {

	@Test
	public void testMarshalling() throws JAXBException {

		final JAXBContext jc = JAXBContext.newInstance(Country.class);
		final StringWriter sw = new StringWriter();
		final Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(Country.France, sw);
		marshaller.marshal(Country.United_Kingdom, sw);
		marshaller.marshal(Country.Reunion, sw);
		marshaller.marshal(Country.Argentina, sw);
		System.out.println(sw.toString());

	}

}
