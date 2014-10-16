package com.temple.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.temple.Module;

public abstract class AbstractLogger {

	private final String prefix;

	private final Logger logger;

	protected AbstractLogger() {
		this(Module.DEFAULT);
	}

	protected AbstractLogger(String prefix) {
		this(prefix, Module.DEFAULT);
	}

	protected AbstractLogger(Module module) {
		this(null, module);
	}

	protected AbstractLogger(String prefix, Module module) {
		this.prefix = this.getClass().getSimpleName() + (prefix == null ? " " : " " + prefix + " - ");
		this.logger = Logger.getLogger(module.packageName + '.' + this.getClass().getSimpleName());
	}

	protected boolean isDebugLoggable() {
		return this.logger.isLoggable(Level.FINE);
	}

	protected boolean isInfoLoggable() {
		return this.logger.isLoggable(Level.INFO);
	}

	protected boolean isWarningLoggable() {
		return this.logger.isLoggable(Level.WARNING);
	}

	protected boolean isErrorLoggable() {
		return this.logger.isLoggable(Level.SEVERE);
	}

	protected void debug(Object arg0) {
		this.log(Level.FINE, arg0);
	}

	protected void info(Object arg0) {
		this.log(Level.INFO, arg0);
	}

	protected void warning(Object arg0) {
		this.log(Level.WARNING, arg0);
	}

	protected void error(Object arg0) {
		this.log(Level.SEVERE, arg0);
	}

	protected void logThrowable(Throwable t) {
		this.logThrowable(t.getMessage(), t);
	}

	protected void logThrowable(String msg, Throwable t) {
		this.logger.log(Level.SEVERE, msg, t);
	}

	private void log(Level l, Object toLog) {
		final StringBuilder msg = new StringBuilder(this.prefix);
		if (toLog != null) {
			msg.append(toLog.toString());
		}
		this.logger.log(l, msg.toString());
	}
}
