package com.temple.web.cdi.request;

import com.temple.model.geo.Location;
import com.temple.service.cdi.TempleObject;
import com.temple.service.model.FindEntityException;
import com.temple.service.model.geo.LocationManager;
import com.temple.util.geo.Country;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
@Model
@TempleObject
public class LocationBean extends AbstractRequestBean {

	private static final long serialVersionUID = 1L;

	private static final Location[] empty = {};

	@EJB
	private LocationManager locationManager;

	/**
	 * TODOC
	 *
	 * @return
	 */
	public Location[] getAllRegions(Country c) {
		try {
			return this.locationManager.getRegions(c.ordinal());
		} catch (final FindEntityException e) {
			this.addError(e);
			return LocationBean.empty;
		}
	}

	/**
	 * TODOC
	 *
	 * @return
	 */
	public Location[] getAllCities(Country c, int regionId) {
		try {
			return this.locationManager.getCities(c.ordinal(), regionId);
		} catch (final FindEntityException e) {
			this.addError(e);
			return LocationBean.empty;
		}
	}
}
