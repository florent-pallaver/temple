package com.temple.service.cdi;

import com.temple.AbstractTempleBean;
import com.temple.Module;

public abstract class AbstractCDIBean extends AbstractTempleBean implements TempleCDIBean {

	private static final long serialVersionUID = 1L;

	protected AbstractCDIBean() {
		super(Module.SERVICE);
	}

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
