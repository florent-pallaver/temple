package com.temple;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.temple.util.AbstractLogger;

public abstract class AbstractTempleBean extends AbstractLogger {

	private static volatile int nextId = 0;

	private long creationTime;

	protected AbstractTempleBean() {
		super();
	}

	protected AbstractTempleBean(String loggerName) {
		this(loggerName, Integer.toString(AbstractTempleBean.nextId++));
	}

	protected AbstractTempleBean(String loggerName, String logPrefix) {
		super(loggerName, logPrefix);
	}

	protected AbstractTempleBean(Module module) {
		this(Integer.toString(AbstractTempleBean.nextId++), module);
	}

	protected AbstractTempleBean(String prefix, Module module) {
		super(module.packageName + " " + prefix);
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
		if (this.isDebugLoggable()) {
			this.debug("Constructed");
		}
		this.creationTime = System.currentTimeMillis();
	}
}
