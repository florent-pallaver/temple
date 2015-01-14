package com.temple.web.cdi.request;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import com.temple.web.cdi.session.AbstractSessionBean;

/**
 * TODOC
 * To be implemented by class in scope request cause the binding cause inconsistent behavior
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class AbstractRequestBean extends AbstractSessionBean {

	private static final long serialVersionUID = 1L;

	@Inject
	private HttpServletRequest req;

	protected boolean getBooleanParam(String key) {
		final String value = this.req.getParameter(key);
		return value != null && value.equals("1");
	}

	protected Integer getIntegerParam(String key) {
		final String value = this.req.getParameter(key);
		return value != null ? Integer.valueOf(value) : null;
	}

	protected Integer[] getIntegerArrayParam(String key) {
		final Integer[] ints;
		final String[] values = this.req.getParameterValues(key);
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
