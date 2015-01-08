package com.temple.web.cdi.request;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import com.temple.LocaleViewableTempleException;
import com.temple.cdi.TempleBean;
import com.temple.cdi.session.LanguageBean;
import com.temple.web.cdi.AbstractTempleWebBean;

/**
 * TODOC
 * To be implemented by class in scope request cause the binding cause inconsistent behavior
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class AbstractRequestBean extends AbstractTempleWebBean {

	private static final String warningMessagesClientId = "warningMessages";

	private static final String errorMessagesClientId = "errorMessages";

	@Inject
	@TempleBean
	private LanguageBean languageBean;

	@Inject
	private HttpServletRequest req;

	protected Integer getIntegerParam(String key) {
		final String value = this.req.getParameter(key);
		return value != null ? Integer.valueOf(value) : null;
	}

	protected Integer[] getIntegerArrayParam(String key) {
		final Integer[] ints;
		final String[] values = this.req.getParameterValues(key);
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

	protected void addWarningMessage(String clientId, String key, Object... parameters) {
		this.addWarning(clientId == null ? AbstractRequestBean.warningMessagesClientId : clientId, this.languageBean.getString(key, parameters));
	}

	/**
	 * TODOC
	 *
	 * @param e
	 */
	protected void addErrorMessage(LocaleViewableTempleException e) {
		this.addErrorMessage(AbstractRequestBean.errorMessagesClientId, e, true);
	}

	/**
	 * TODOC
	 *
	 * @param e
	 */
	protected void addErrorMessage(String clientId, LocaleViewableTempleException e) {
		this.addErrorMessage(clientId, e, true);
	}

	/**
	 * TODOC
	 *
	 * @param e
	 */
	protected void addErrorMessage(String clientId, LocaleViewableTempleException e, boolean logException) {
		if (logException) {
			this.logThrowable(e);
		}
		this.addError(clientId, this.languageBean.getString(e));
	}

	/**
	 * TODOC
	 *
	 * @param context
	 * @param component
	 * @param messageKey
	 */
	protected void addErrorMessage(FacesContext context, UIComponent component, String messageKey) {
		((UIInput) component).setValid(false);
		this.addError(component.getClientId(context), this.languageBean.getString(messageKey));
	}

	/**
	 * TODOC
	 *
	 * @param clientId
	 * @param messageKey
	 * @param parameters
	 */
	protected void addErrorMessage(String clientId, String messageKey, Object... parameters) {
		this.addError(clientId == null ? AbstractRequestBean.errorMessagesClientId : clientId, this.languageBean.getString(messageKey, parameters));
	}

	/**
	 * TODOC
	 *
	 * @param clientId
	 * @param e
	 */
	protected void addFatal(String clientId, Exception e) {
		this.logThrowable(e);
		this.addErrorMessage(null, "com.pc.fatal");
	}
}
