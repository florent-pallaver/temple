package com.temple.cdi.impl;

import com.temple.AbstractTempleBean;
import com.temple.Module;
import com.temple.cdi.TempleCDIBean;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class AbstractCDIBean extends AbstractTempleBean implements TempleCDIBean {

	/**
	 * Constructor.
	 */
	protected AbstractCDIBean() {
		super(Module.CDI);
	}

	@Override
	public void touch() {
		if (this.isDebugLoggable()) {
			this.debug("touched");
		}
	}
}
