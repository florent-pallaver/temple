/**
 *
 */
package com.temple.util.geo;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class CountryAdapter extends XmlAdapter<Country, CountryRep> {

	@Override
	public Country marshal(CountryRep v) throws Exception {
		return v == null ? null : Country.getByISOCode(v.key, true);
	}

	@Override
	public CountryRep unmarshal(Country v) throws Exception {
		return v == null ? null : new CountryRep(v.getIsoCode(), v.getName());
	}

}
