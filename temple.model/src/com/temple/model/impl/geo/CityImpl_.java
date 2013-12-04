package com.temple.model.impl.geo;

import com.temple.model.geo.CityId;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-11-11T19:24:41.460+1100")
@StaticMetamodel(CityImpl.class)
public class CityImpl_ extends AbstractGeoEntity_ {
	public static volatile SingularAttribute<CityImpl, CityId> id;
	public static volatile SingularAttribute<CityImpl, RegionImpl> region;
	public static volatile SingularAttribute<CityImpl, String> postCode;
}
