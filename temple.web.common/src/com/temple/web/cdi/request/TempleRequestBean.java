package com.temple.web.cdi.request;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.enterprise.context.RequestScoped;

import com.temple.service.cdi.TempleObject;
import com.temple.service.cdi.TierDependent;
import com.temple.service.cdi.request.RequestBean;
import com.temple.util.geo.Country;
import com.temple.util.geo.HumanSettlement;

/**
 * Web dependent implementation
 *
 * @author flominou
 */
@RequestScoped
@TempleObject
@TierDependent(TierDependent.Tier.WEB)
public class TempleRequestBean extends AbstractRequestBean implements RequestBean {

	private static final long serialVersionUID = 1L ;

	@Override
	public InetAddress getClientIP4() {
		InetAddress ip4 = null;
		final String ip = this.request.getRemoteAddr()  ;
		if(ip != null && !ip.isEmpty()) {
			final String[] ss = ip.split("\\.") ;
			if(ss.length == 4) {
				final byte[] addr = new byte[] { Byte.parseByte(ss[0]), Byte.parseByte(ss[1]), Byte.parseByte(ss[2]), Byte.parseByte(ss[3]) } ;
				try {
					ip4 = InetAddress.getByAddress(addr) ;
				} catch (final UnknownHostException ex) {
					this.thrown("invalid remote address : " + ip, ex);
				}
			} else {
				this.warning("invalid remote address : " + ip);
			}
		} else {
			this.warning("remote address is empty");
		}
		return ip4 ;
	}

	@Override
	public Country getClientCountry() {
		// FIXME
		return Country.France ;
		//		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public HumanSettlement getClientLocation() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
