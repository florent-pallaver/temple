package com.temple.ejb.impl;

import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;

import com.temple.AbstractTempleBean;
import com.temple.Module;
import com.temple.ejb.TempleManager;

/**
 * Base implementation of {@link TempleRemoteEJB}.
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class AbstractManagerBean extends AbstractTempleBean implements TempleManager {

	private static final long serialVersionUID = 1L;

	public AbstractManagerBean() {
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
