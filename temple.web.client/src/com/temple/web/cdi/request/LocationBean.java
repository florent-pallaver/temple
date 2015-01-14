package com.temple.web.cdi.request;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;

import com.temple.cdi.TempleBean;
import com.temple.ejb.model.FindEntityException;
import com.temple.ejb.model.geo.LocationManager;
import com.temple.model.geo.Location;
import com.temple.util.geo.Country;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
@Model
@TempleBean
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
	public Location[] getAllRegions() {
		try {
			return this.locationManager.getRegions(Country.AUSTRALIA.ordinal());
		} catch (final FindEntityException e) {
			this.addErrorMessage(e);
			return LocationBean.empty;
		}
	}

	/**
	 * TODOC
	 *
	 * @return
	 */
	public Location[] getAllCities(int regionId) {
		try {
			return this.locationManager.getCities(Country.AUSTRALIA.ordinal(), regionId);
		} catch (final FindEntityException e) {
			this.addErrorMessage(e);
			return LocationBean.empty;
		}
	}
}
