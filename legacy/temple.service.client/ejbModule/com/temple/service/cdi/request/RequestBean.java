package com.temple.service.cdi.request;

import com.temple.util.geo.Country;
import com.temple.util.geo.HumanSettlement;
import java.io.Serializable;
import java.net.InetAddress;

/**
 * TODOC
 * @author flominou
 */
public interface RequestBean extends Serializable {
	
	/**
	 * @return the client's IP v4
	 */
	InetAddress getClientIP4() ;
	
	/**
	 * @return the client's country
	 */
	Country getClientCountry() ;
	
	/**
	 * @return the client's location.
	 */
	HumanSettlement getClientLocation() ;
	
}
