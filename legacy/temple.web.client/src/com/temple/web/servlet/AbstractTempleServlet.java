package com.temple.web.servlet;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.temple.LocaleViewableTempleException;
import com.temple.service.cdi.ApplicationBean;
import com.temple.service.cdi.session.LanguageBean;
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
	@ApplicationBean
	protected LanguageBean lb;

	private final TempleLogger logger = new TempleLogger();

	/**
	 * Constructor.
	 */
	protected AbstractTempleServlet() {
	}

	// TODO code à factoriser
	protected final Integer getIntegerParam(HttpServletRequest req, String key) {
		final String value = req.getParameter(key);
		Integer i = null;
		if (value != null) {
			try {
				i = Integer.valueOf(value);
			} catch (final Exception ignored) {
			}
		}
		return i;
	}

	protected final boolean getBooleanParam(HttpServletRequest req, String key) {
		final Integer i = this.getIntegerParam(req, key);
		return i != null && i == 1;
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

	protected final void thrown(Throwable t) {
		this.logger.thrown(t);
	}

	protected final void thrown(String msg, Throwable t) {
		this.logger.thrown(msg, t);
	}

	protected final void addError(LocaleViewableTempleException e) {
		FacesContext.getCurrentInstance() //
		.addMessage(null, //
				new FacesMessage(FacesMessage.SEVERITY_ERROR, //
						this.lb.getString(e), //
						this.lb.getDetailedString(e)));
	}
}
