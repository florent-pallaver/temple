package com.temple.web.cdi.request;

import com.temple.web.cdi.session.AbstractSessionBean;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * TODOC
 * To be implemented by class in scope request cause the binding causes inconsistent behavior
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class AbstractRequestBean extends AbstractSessionBean {

	private static final long serialVersionUID = 1L;

	@Inject
	protected HttpServletRequest request;

	protected String getStringParam(String key) {
		return this.request.getParameter(key) ;
	}
	
	protected boolean getBooleanParam(String key) {
		final String value = this.request.getParameter(key);
		return value != null && value.equals("1");
	}

	protected Integer getIntegerParam(String key) {
		return this.getIntegerParam(key, null) ;
	}

	protected Integer getIntegerParam(String key, Integer d) {
		final String value = this.request.getParameter(key);
		Integer i = null ;
		if(value != null) {
			try {
				i = Integer.valueOf(value) ;
			} catch(NumberFormatException e) {
				this.ignored(e);
			}
		}
		return i == null ? d : i ;
	}

	/**
	 * 
	 * @param key
	 * @return never <code>null</code>
	 */
	protected Integer[] getIntegerArrayParam(String key) {
		final Integer[] ints;
		final String[] values = this.request.getParameterValues(key);
		if (values != null) {
			ints = new Integer[values.length];
			for (int i = values.length; i-- > 0;) {
				ints[i] = Integer.valueOf(values[i]);
			}
		} else {
			ints = new Integer[0];
		}
		return ints;
	}
}
