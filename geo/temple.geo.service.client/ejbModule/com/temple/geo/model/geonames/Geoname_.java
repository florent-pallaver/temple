package com.temple.geo.model.geonames;

import com.temple.util.geo.Country;
import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-01-28T17:12:28.055+0100")
@StaticMetamodel(Geoname.class)
public class Geoname_ {
	public static volatile SingularAttribute<Geoname, Integer> id;
	public static volatile SingularAttribute<Geoname, String> name;
	public static volatile SingularAttribute<Geoname, Double> latitude;
	public static volatile SingularAttribute<Geoname, Double> longitude;
	public static volatile SingularAttribute<Geoname, FeatureType> featureType;
	public static volatile SingularAttribute<Geoname, Feature> feature;
	public static volatile SingularAttribute<Geoname, Country> country;
	public static volatile SingularAttribute<Geoname, String> ac1;
	public static volatile SingularAttribute<Geoname, String> ac2;
	public static volatile SingularAttribute<Geoname, String> ac3;
	public static volatile SingularAttribute<Geoname, String> ac4;
	public static volatile SingularAttribute<Geoname, Long> population;
	public static volatile SingularAttribute<Geoname, Integer> elevation;
	public static volatile SingularAttribute<Geoname, Integer> dem;
	public static volatile SingularAttribute<Geoname, String> timezone;
	public static volatile SingularAttribute<Geoname, Calendar> modification_date;
}
