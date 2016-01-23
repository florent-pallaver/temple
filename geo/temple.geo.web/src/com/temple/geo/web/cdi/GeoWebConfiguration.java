/**
 * 
 */
package com.temple.geo.web.cdi;

import java.io.File;
import java.util.List;

import com.temple.Module;
import com.temple.service.cdi.AbstractCDIBean;
import com.temple.web.cdi.WebConfiguration;

/**
 * @author flominou
 *
 */
public class GeoWebConfiguration extends AbstractCDIBean implements WebConfiguration {

	private static final long serialVersionUID = 1L;

	GeoWebConfiguration() {
		super(Module.WEB);
	}

	@Override
	public int getPageParameterIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getHomePage() {
		return "index" ;
	}

	/* (non-Javadoc)
	 * @see com.temple.web.cdi.WebConfiguration#getCommonPages()
	 */
	@Override
	public List<String> getCommonPages() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.temple.web.cdi.WebConfiguration#getSessionPages()
	 */
	@Override
	public List<String> getSessionPages() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.temple.web.cdi.WebConfiguration#getStaticResourcePathPrefix()
	 */
	@Override
	public String getStaticResourcePathPrefix() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.temple.web.cdi.WebConfiguration#getTempFolder()
	 */
	@Override
	public File getTempFolder() {
		// TODO Auto-generated method stub
		return null;
	}

}
