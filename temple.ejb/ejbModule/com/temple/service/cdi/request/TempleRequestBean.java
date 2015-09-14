package com.temple.service.cdi.request;

import com.temple.service.cdi.AbstractCDIBean;
import com.temple.service.cdi.TempleObject;
import com.temple.service.cdi.TierDependent;
import com.temple.util.geo.Country;
import com.temple.util.geo.HumanSettlement;
import java.net.Inet4Address;
import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.enterprise.context.RequestScoped;

/**
 * EJB dependent implementation
 * 
 * @author flominou
 */
@RequestScoped
@TempleObject
@TierDependent(TierDependent.Tier.EJB)
public class TempleRequestBean extends AbstractCDIBean implements RequestBean {

	private static final long serialVersionUID = 1L ;
	
	@Resource
	private EJBContext ejbContext ;

	@Override
	public Inet4Address getClientIP4() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Country getClientCountry() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
	@Override
	public HumanSettlement getClientLocation() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

}
