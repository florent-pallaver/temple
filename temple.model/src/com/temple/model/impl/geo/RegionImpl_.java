package com.temple.model.impl.geo;

import com.temple.model.geo.RegionId;
import com.temple.util.geo.Country;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-01-22T16:45:15.967+0100")
@StaticMetamodel(RegionImpl.class)
public class RegionImpl_ extends AbstractGeoEntity_ {
	public static volatile SingularAttribute<RegionImpl, RegionId> id;
	public static volatile SingularAttribute<RegionImpl, Country> country;
}
