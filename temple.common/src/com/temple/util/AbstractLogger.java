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

	protected boolean isFinestLoggable() {
		return this.logger.isLoggable(Level.FINEST);
	}

	protected boolean isFinerLoggable() {
		return this.logger.isLoggable(Level.FINER);
	}

	protected boolean isFineLoggable() {
		return this.logger.isLoggable(Level.FINE);
	}

	protected boolean isConfigLoggable() {
		return this.logger.isLoggable(Level.CONFIG);
	}

	protected boolean isInfoLoggable() {
		return this.logger.isLoggable(Level.INFO);
	}

	protected boolean isWarningLoggable() {
		return this.logger.isLoggable(Level.WARNING);
	}

	protected boolean isSevereLoggable() {
		return this.logger.isLoggable(Level.SEVERE);
	}

	protected void finest(Object arg0) {
		this.log(Level.FINEST, arg0);
	}

	protected void finer(Object arg0) {
		this.log(Level.FINER, arg0);
	}

	protected void fine(Object arg0) {
		this.log(Level.FINE, arg0);
	}

	protected void config(Object arg0) {
		this.log(Level.CONFIG, arg0);
	}

	protected void info(Object arg0) {
		this.log(Level.INFO, arg0);
	}

	protected void warning(Object arg0) {
		this.log(Level.WARNING, arg0);
	}

	protected void severe(Object arg0) {
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
