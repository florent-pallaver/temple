package com.temple.web.cdi.session;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.temple.LocaleViewableTempleException;
import com.temple.LocalizedTempleException;
import com.temple.service.ServiceException;
import com.temple.service.cdi.TempleBean;
import com.temple.service.cdi.session.LanguageBean;
import com.temple.view.LocaleStringView;
import com.temple.web.cdi.application.AbstractApplicationBean;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class AbstractSessionBean extends AbstractApplicationBean {

	private static final long serialVersionUID = 1L;

	@Inject
	@TempleBean
	protected LanguageBean languageBean;

	@Override
	protected void serviceException(final ServiceException e) {
		this.addError(this.languageBean.getStringView(e));
	}

	protected void addWarningMessage(String clientId, String key, Object... parameters) {
		this.addWarning(clientId, this.languageBean.getString(key, parameters));
	}

	/**
	 * TODOC
	 *
	 * @param e
	 */
	protected void addErrorMessage(LocaleViewableTempleException e) {
		this.addErrorMessage(null, e, true);
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
			this.thrown(e);
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
		this.addError(clientId, this.languageBean.getString(messageKey, parameters));
	}

	/**
	 * TODOC
	 *
	 * @param clientId
	 * @param e
	 */
	protected void addFatal(String clientId, Exception e) {
		this.thrown(e);
		this.addErrorMessage(null, "com.pc.fatal");
	}

	/**
	 * TODOC
	 *
	 * @param summary
	 */
	protected void addInfo(String summary) {
		this.addInfo(null, summary);
	}

	/**
	 * TODOC
	 *
	 * @param id
	 * @param summary
	 */
	protected void addInfo(String id, String summary) {
		this.addMesage(id, FacesMessage.SEVERITY_INFO, summary, null);
	}

	/**
	 * TODOC
	 *
	 * @param summary
	 */
	protected void addWarning(String summary) {
		this.addWarning(null, summary);
	}

	/**
	 * TODOC
	 *
	 * @param id
	 * @param summary
	 */
	protected void addWarning(String id, String summary) {
		this.addMesage(id, FacesMessage.SEVERITY_WARN, summary, null);
	}

	/**
	 * TODOC
	 *
	 * @param e
	 */
	protected void addError(Exception e) {
		this.addError(e.getLocalizedMessage());
	}

	/**
	 * TODOC
	 *
	 * @param slv
	 */
	protected void addError(LocaleStringView slv) {
		this.addError(null, slv);
	}

	/**
	 * TODOC
	 *
	 * @param id
	 * @param slv
	 */
	protected void addError(String id, LocaleStringView slv) {
		this.addMesage(id, FacesMessage.SEVERITY_ERROR, slv);
	}

	/**
	 * TODOC
	 *
	 * @param summary
	 */
	protected void addError(String summary) {
		this.addError(null, summary);
	}

	/**
	 * TODOC
	 *
	 * @param id
	 * @param summary
	 */
	protected void addError(String id, String summary) {
		this.addMesage(id, FacesMessage.SEVERITY_ERROR, summary, null);
	}

	/**
	 * TODOC
	 *
	 * @param e
	 */
	protected void addFatal(LocalizedTempleException e) {
		this.addFatal(e.getLocalizedMessage());
	}

	/**
	 * TODOC
	 *
	 * @param summary
	 */
	protected void addFatal(String summary) {
		this.addFatal(null, summary);
	}

	/**
	 * TODOC
	 *
	 * @param id
	 * @param summary
	 */
	protected void addFatal(String id, String summary) {
		this.addMesage(id, FacesMessage.SEVERITY_FATAL, summary, null);
	}

	private final void addMesage(String id, Severity s, String l, String d) {
		FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(s, l, d));
	}

	private final void addMesage(String id, Severity s, LocaleStringView slv) {
		this.addMesage(id, s, slv.getString(), slv.getDetailedString());
	}
}
