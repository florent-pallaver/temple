package com.temple.service.ejb;

import com.temple.AbstractTempleBean;
import com.temple.Module;
import com.temple.service.TempleManager;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;

/**
 * Base implementation of {@link TempleManager}.
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class AbstractEJBBean extends AbstractTempleBean implements TempleManager {

	private static final long serialVersionUID = 1L;

	public AbstractEJBBean() {
		super(Module.SERVICE);
	}

	@Override
	public void touch() {
		this.info("Touched");
	}

	@PostActivate
	protected void postActivate() {
		this.info("Activated");
	}

	@PrePassivate
	protected void prePassivate() {
		this.info("About to be passivated");
	}

	@Remove(retainIfException = false)
	protected void preRemove() {
		this.info("About to be removed");
	}
}
