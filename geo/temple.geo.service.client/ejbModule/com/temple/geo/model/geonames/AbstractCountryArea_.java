package com.temple.geo.model.geonames;

import com.temple.util.geo.Country;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-06T18:12:27.020+0200")
@StaticMetamodel(AbstractCountryArea.class)
public class AbstractCountryArea_ {
	public static volatile SingularAttribute<AbstractCountryArea, Integer> id;
	public static volatile SingularAttribute<AbstractCountryArea, String> name;
	public static volatile SingularAttribute<AbstractCountryArea, Double> latitude;
	public static volatile SingularAttribute<AbstractCountryArea, Double> longitude;
	public static volatile SingularAttribute<AbstractCountryArea, Feature> feature;
	public static volatile SingularAttribute<AbstractCountryArea, Country> country;
	public static volatile SingularAttribute<AbstractCountryArea, AdministrativeDivision> parentDivision;
	public static volatile SingularAttribute<AbstractCountryArea, Integer> parentId;
}
