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
	public void debug(Object o) {
		super.debug(o);
	}

	@Override
	public void debug(String format, Object... params) {
		// TODO Auto-generated method stub
		super.debug(format, params);
	}

	@Override
	public void info(Object o) {
		super.info(o);
	}

	@Override
	public void info(String format, Object... params) {
		super.info(format, params);
	}

	@Override
	public void warning(Object o) {
		super.warning(o);
	}

	@Override
	public void warning(String format, Object... params) {
		super.warning(format, params);
	}

	@Override
	public void error(Object o) {
		super.error(o);
	}

	@Override
	public void error(String format, Object... params) {
		super.error(format, params);
	}

	@Override
	public void thrown(Throwable t) {
		super.thrown(t);
	}

	@Override
	public void thrown(String msg, Throwable t) {
		super.thrown(msg, t);
	}

	@Override
	public void report(Throwable t) {
		super.report(t);
	}

	@Override
	public void report(Object o) {
		super.report(o);
	}

	@Override
	public void ignored(Throwable t) {
		super.ignored(t);
	}

}
