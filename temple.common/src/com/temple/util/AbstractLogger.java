package com.temple.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.temple.NotImplementedException;

/**
 * Base implementation class providing util logging methods.
 * 
 * @author flominou
 */
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

	protected void debug(Object o) {
		this.log(Level.FINEST, o);
	}

	protected void debug(String format, Object... params) {
		this.debug(String.format(format, params));
	}

	protected void info(Object o) {
		this.log(Level.INFO, o);
	}

	protected void info(String format, Object... params) {
		this.info(String.format(format, params));
	}

	protected void warning(Object o) {
		this.log(Level.WARNING, o);
	}

	protected void warning(String format, Object... params) {
		this.warning(String.format(format, params));
	}

	/**
	 * Logs the string representation of the given object as {@link Level#SEVERE severe}
	 * @param o
	 */
	protected void error(Object o) {
		this.log(Level.SEVERE, o);
	}

	protected void error(String format, Object... params) {
		this.error(String.format(format, params));
	}

	/**
	 * Logs as {@link Level#SEVERE severe} a {@link Throwable}.
	 *
	 * @param t - the {@link Throwable}
	 */
	protected void thrown(Throwable t) {
		this.thrown(t.getMessage(), t);
	}

	/**
	 * Logs as {@link Level#SEVERE severe} a {@link Throwable} with a message.
	 *
	 * @param msg the message
	 * @param t - the {@link Throwable}
	 */
	protected void thrown(String msg, Throwable t) {
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
		throw new NotImplementedException() ;
	}

	/**
	 * Ignores a {@link Throwable}. It will be logged only if {@link Level#ALL} is loggable.
	 * @param t the {@link Throwable} to ignore
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
