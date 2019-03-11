package com.temple.geo.model.geonames;

import com.temple.util.Nameable;

/**
 * TODOC
 * @author flominou
 */
public enum FeatureType implements Nameable {

	X("Unknown", "undefined feature"),
	A("Administrative", "country, state, region, ..."),
	H("Hydrographic", "Stream, lake, ..."),
	L("Localities", "parks,area, ..."),
	P("Populated places", "city, village,..."),
	R("Transportation", "road, railroad"),
	S("Spot", "spot, building, farm, ..."),
	T("Hypsographic", "mountain,hill,rock, ..."),
	U("Undersea", "undersea stuff ..."),
	V("Vegetation", "forest,heath, ...") ;

	private final String name ;

	private final String desc ;
	
	private FeatureType(String name, String description) {
		this.name = name ;
		this.desc = description ;
	}

	@Override
	public String getName() {
		return this.name ;
	}
	
	/**
	 * @return the description ;
	 */
	public String getDescription() {
		return desc;
	}
	
}
