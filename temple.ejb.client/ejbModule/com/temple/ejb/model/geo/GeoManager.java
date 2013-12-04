package com.temple.ejb.model.geo;

import com.temple.ejb.TempleManager;
import com.temple.ejb.model.FindEntityException;
import com.temple.model.geo.City;
import com.temple.model.geo.CityId;
import com.temple.model.geo.Region;
import com.temple.model.geo.RegionId;
import com.temple.util.geo.Country;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public interface GeoManager extends TempleManager {

	/**
	 * TODOC
	 * 
	 * @param regionId
	 * @return
	 * @throws FindEntityException
	 */
	Region findRegion(RegionId regionId) throws FindEntityException;

	/**
	 * TODOC
	 * 
	 * @param country
	 * @return
	 * @throws FindEntityException
	 */
	Region[] findRegions(Country country) throws FindEntityException;

	/**
	 * TODOC
	 * 
	 * @param c
	 * @param regionId
	 * @param cityId
	 * @return
	 * @throws FindEntityException
	 */
	City findCity(CityId cityId) throws FindEntityException;

	/**
	 * TODOC
	 * 
	 * @param regionId
	 * @return
	 * @throws FindEntityException
	 */
	City[] findCities(RegionId regionId) throws FindEntityException;
}
