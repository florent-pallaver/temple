package com.temple.util;

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
	 * 
	 * @param loggerName
	 * @param prefix 
	 */
	public TempleLogger(String loggerName, String prefix) {
		super(loggerName, prefix);
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
	public void thrown(Throwable t) {
		super.thrown(t);
	}

	@Override
	public void throwable(String msg, Throwable t) {
		super.throwable(msg, t);
	}
}
