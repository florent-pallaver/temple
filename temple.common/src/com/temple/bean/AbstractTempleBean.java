package com.temple.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.temple.Module;
import com.temple.util.AbstractLogger;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class AbstractTempleBean extends AbstractLogger {

	private static volatile int nextId = 0;

	/**
	 * Constructor.
	 */
	AbstractTempleBean() {
		super();
	}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param module
	 */
	protected AbstractTempleBean(Module module) {
		this(Integer.toString(AbstractTempleBean.nextId++), module);
	}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param prefix
	 * @param module
	 */
	protected AbstractTempleBean(String prefix, Module module) {
		super(prefix, module);
	}

	@PreDestroy
	protected void preDestroy() {
		this.info("About to be destroyed");
	}

	@PostConstruct
	protected void postConstruct() {
		this.info("Constructed");
	}
}
