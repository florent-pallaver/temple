package com.temple.web.servlet;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.temple.LocaleViewableTempleException;
import com.temple.Module;
import com.temple.cdi.TempleBean;
import com.temple.cdi.session.LanguageBean;
import com.temple.util.TempleLogger;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class AbstractTempleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Inject
	@TempleBean
	protected LanguageBean lb;

	private final TempleLogger logger = new TempleLogger(this.getClass().getSimpleName(), Module.WEB, this.getClass().getSimpleName());

	/**
	 * Constructor.
	 */
	protected AbstractTempleServlet() {}

	protected final Integer getIntegerParam(HttpServletRequest req, String key) {
		final String value = req.getParameter(key);
		return value != null ? Integer.valueOf(value) : null;
	}

	protected final Integer[] getIntegerArrayParam(HttpServletRequest req, String key) {
		final Integer[] ints;
		final String[] values = req.getParameterValues(key);
		if (values != null) {
			ints = new Integer[values.length];
			for (int i = values.length; i-- > 0;) {
				ints[i] = Integer.valueOf(values[i]);
			}
		} else {
			ints = new Integer[0];
		}
		return ints;
	}

	protected final boolean isDebugLoggable() {
		return this.logger.isDebugLoggable();
	}

	protected final boolean isInfoLoggable() {
		return this.logger.isInfoLoggable();
	}

	protected final boolean isWarningLoggable() {
		return this.logger.isWarningLoggable();
	}

	protected final boolean isErrorLoggable() {
		return this.logger.isErrorLoggable();
	}

	protected final void debug(Object arg0) {
		this.logger.debug(arg0);
	}

	protected final void info(Object arg0) {
		this.logger.info(arg0);
	}

	protected final void warning(Object arg0) {
		this.logger.warning(arg0);
	}

	protected final void error(Object arg0) {
		this.logger.error(arg0);
	}

	protected final void logThrowable(Throwable t) {
		this.logger.throwable(t);
	}

	protected final void logThrowable(String msg, Throwable t) {
		this.logger.throwable(msg, t);
	}

	protected final void addError(LocaleViewableTempleException e) {
		FacesContext.getCurrentInstance() //
				.addMessage(null, //
						new FacesMessage(FacesMessage.SEVERITY_ERROR, //
								this.lb.getString(e), //
								this.lb.getDetailedString(e)));
	}
}
