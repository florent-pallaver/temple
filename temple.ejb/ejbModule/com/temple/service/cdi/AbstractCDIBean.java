package com.temple.service.cdi;

import com.temple.AbstractTempleBean;
import com.temple.Module;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class AbstractCDIBean extends AbstractTempleBean implements TempleCDIBean {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 */
	protected AbstractCDIBean() {
		super(Module.CDI);
	}

	/**
	 * Constructor.
	 * TODOC
	 *
	 * @param module
	 */
	protected AbstractCDIBean(Module module) {
		super(module);
	}

	@Override
	public void touch() {
		if (this.isDebugLoggable()) {
			this.debug("touched");
		}
	}
}
