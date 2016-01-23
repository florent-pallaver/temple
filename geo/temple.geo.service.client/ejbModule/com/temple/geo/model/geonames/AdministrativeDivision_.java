package com.temple.geo.model.geonames;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-06T18:16:45.121+0200")
@StaticMetamodel(AdministrativeDivision.class)
public class AdministrativeDivision_ extends AbstractCountryArea_ {
	public static volatile ListAttribute<AdministrativeDivision, AdministrativeDivision> divisions;
	public static volatile SingularAttribute<AdministrativeDivision, Integer> divisionsCount;
	public static volatile ListAttribute<AdministrativeDivision, PopulatedPlace> places;
	public static volatile SingularAttribute<AdministrativeDivision, Integer> placesCount;
}
