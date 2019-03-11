package com.temple.service.ws;

import com.temple.AbstractTempleBean;
import com.temple.Module;
import com.temple.service.TempleManager;

/**
 * Base implementation of {@link TempleManager}.
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class AbstractWebServiceBean extends AbstractTempleBean implements TempleManager {

	private static final long serialVersionUID = 1L;

	public AbstractWebServiceBean() {
		super(Module.SERVICE);
	}

	@Override
	public void touch() {
		this.info("Touched");
	}

}
