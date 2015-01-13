package com.temple.util;

import com.temple.Module;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public final class TempleLogger extends AbstractLogger {

	/**
	 * Constructor.
	 */
	public TempleLogger() {
		super();
	}

	/**
	 * Constructor.
	 * TODOC
	 *
	 * @param prefix
	 */
	public TempleLogger(String prefix) {
		super(prefix);
	}

	/**
	 * Constructor.
	 * TODOC
	 *
	 * @param module
	 */
	public TempleLogger(Module module) {
		super(module);
	}

	/**
	 * Constructor.
	 * TODOC
	 *
	 * @param prefix
	 * @param module
	 */
	public TempleLogger(String prefix, Module module) {
		super(prefix, module);
	}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param prefix
	 * @param module
	 * @param suffix
	 */
	public TempleLogger(String prefix, Module module, String suffix) {
		super(prefix, module, suffix);
	}

	@Override
	public boolean isDebugLoggable() {
		return super.isDebugLoggable();
	}

	@Override
	public boolean isInfoLoggable() {
		return super.isInfoLoggable();
	}

	@Override
	public boolean isWarningLoggable() {
		return super.isWarningLoggable();
	}

	@Override
	public boolean isErrorLoggable() {
		return super.isErrorLoggable();
	}

	@Override
	public void debug(Object arg0) {
		super.debug(arg0);
	}

	@Override
	public void info(Object arg0) {
		super.info(arg0);
	}

	@Override
	public void warning(Object arg0) {
		super.warning(arg0);
	}

	@Override
	public void error(Object arg0) {
		super.error(arg0);
	}

	@Override
	public void throwable(Throwable t) {
		super.throwable(t);
	}

	@Override
	public void throwable(String msg, Throwable t) {
		super.throwable(msg, t);
	}
}
