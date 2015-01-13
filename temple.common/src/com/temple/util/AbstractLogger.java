package com.temple.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.temple.Module;

public abstract class AbstractLogger {

	private final String prefix;

	private final Logger logger;

	protected AbstractLogger() {
		this((String) null);
	}

	protected AbstractLogger(String prefix) {
		this(null, prefix);
	}

	@Deprecated
	protected AbstractLogger(Module module) {
		this(null, module);
	}

	@Deprecated
	protected AbstractLogger(String prefix, Module module) {
		this(prefix, module, null);
	}

	@Deprecated
	protected AbstractLogger(String prefix, Module module, String suffix) {
		final String s = suffix == null ? this.getClass().getSimpleName() : suffix;
		this.prefix = s + (prefix == null ? " " : " " + prefix + " - ");
		this.logger = Logger.getLogger(module.packageName + '.' + s);
	}

	protected AbstractLogger(String loggerName, String prefix) {
		this.prefix = prefix == null ? null : " " + prefix + " - ";
		this.logger = Logger.getLogger(loggerName == null ? this.getClass().getName() : loggerName);
	}

	protected boolean isDebugLoggable() {
		return this.logger.isLoggable(Level.FINEST);
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
		this.log(Level.FINEST, arg0);
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

	protected void throwable(Throwable t) {
		this.throwable(t.getMessage(), t);
	}

	protected void throwable(String msg, Throwable t) {
		this.logger.log(Level.SEVERE, msg, t);
	}

	private void log(Level l, Object toLog) {
		if (toLog != null) {
			final Object log;
			if (this.prefix == null) {
				log = toLog;
			} else {
				log = new StringBuilder(this.prefix).append(toLog.toString());
			}
			this.logger.log(l, log.toString());
		}
	}
}
