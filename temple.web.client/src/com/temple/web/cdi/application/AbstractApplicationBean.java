package com.temple.web.cdi.application;

import java.io.Serializable;

import com.temple.ejb.ServiceCaller;
import com.temple.ejb.ServiceException;
import com.temple.web.cdi.AbstractTempleWebBean;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class AbstractApplicationBean extends AbstractTempleWebBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * TODOC
	 *
	 * @param sc
	 * @see #serviceException(ServiceException)
	 */
	protected void execute(ServiceCaller sc) {
		try {
			sc.call();
		} catch (final ServiceException e) {
			this.serviceException(e);
		}
	}

	protected void serviceException(final ServiceException e) {
		this.thrown(e);
	}
}
