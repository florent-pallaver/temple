package com.temple.web.cdi;

import com.temple.AbstractTempleBean;
import com.temple.Module;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class AbstractTempleWebBean extends AbstractTempleBean {

	/**
	 * Constructor.
	 */
	protected AbstractTempleWebBean() {
		super(Module.WEB);
	}

	/**
	 * No op
	 */
	public void noop() {}
}
