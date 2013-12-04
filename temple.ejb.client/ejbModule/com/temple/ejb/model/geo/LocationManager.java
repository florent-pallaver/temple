package com.temple.ejb.model.geo;

import com.temple.ejb.TempleManager;
import com.temple.ejb.model.FindEntityException;
import com.temple.model.geo.Location;
import com.temple.model.geo.LocationId;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public interface LocationManager extends TempleManager {

	/**
	 * TODOC
	 * 
	 * @return
	 * @throws FindEntityException
	 */
	Location[] getCountries() throws FindEntityException;

	/**
	 * TODOC
	 * 
	 * @param countryId
	 * @return
	 * @throws FindEntityException
	 */
	Location[] getRegions(int countryId) throws FindEntityException;

	/**
	 * TODOC
	 * 
	 * @param countryId
	 * @param regionId
	 * @return
	 * @throws FindEntityException
	 */
	Location[] getCities(int countryId, int regionId) throws FindEntityException;

	/**
	 * TODOC
	 * 
	 * @param lid
	 * @return
	 * @throws FindEntityException
	 */
	Location find(LocationId lid) throws FindEntityException;
}
