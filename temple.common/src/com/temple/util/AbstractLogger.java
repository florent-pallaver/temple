package com.temple.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractLogger {

	private final String prefix;

	private final Logger logger;

	protected AbstractLogger() {
		this((String) null);
	}

	protected AbstractLogger(String prefix) {
		this(null, prefix);
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

	/**
	 * Logs the string representation of the given object as {
	 *
	 * @Level#SEVERE severe}
	 * @param arg0
	 */
	protected void error(Object arg0) {
		this.log(Level.SEVERE, arg0);
	}

	protected void thrown(Throwable t) {
		this.throwable(t.getMessage(), t);
	}

	protected void throwable(String msg, Throwable t) {
		this.logger.log(Level.SEVERE, msg, t);
	}

	protected void report(Throwable t) {
		this.sendReport();
		this.thrown(t);
	}

	protected void report(Object o) {
		this.sendReport();
		this.error(o);
	}

	private void sendReport() {
		// FIXME
	}

	/**
	 * Ignores a {
	 *
	 * @Throwable}. It will be logged only if Level.ALL is loggable.
	 * @param t the {
	 * @Throwable} to ignore
	 */
	protected void ignored(Throwable t) {
		if (this.logger.isLoggable(Level.ALL)) {
			this.logger.log(Level.ALL, "Throwable ignored", t);
		}
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
