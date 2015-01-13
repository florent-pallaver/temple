package com.temple;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.temple.util.AbstractLogger;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class AbstractTempleBean extends AbstractLogger {

	private static volatile int nextId = 0;

	private long creationTime;

	/**
	 * Constructor.
	 */
	AbstractTempleBean() {
		super();
	}

	/**
	 * Constructor.
	 *
	 * @param prefix
	 */
	protected AbstractTempleBean(String loggerName) {
		this(loggerName, Integer.toString(AbstractTempleBean.nextId++));
	}

	/**
	 * Constructor.
	 *
	 * @param prefix
	 */
	protected AbstractTempleBean(String loggerName, String logPrefix) {
		super(loggerName, logPrefix);
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
		super(module.packageName, prefix);
	}

	@PreDestroy
	protected void preDestroy() {
		if (this.isDebugLoggable()) {
			this.debug("Lived for " + (System.currentTimeMillis() - this.creationTime) + " ms");
			this.debug("About to be destroyed");
		}
	}

	@PostConstruct
	protected void postConstruct() {
		this.debug("Constructed");
		this.creationTime = System.currentTimeMillis();
	}
}
