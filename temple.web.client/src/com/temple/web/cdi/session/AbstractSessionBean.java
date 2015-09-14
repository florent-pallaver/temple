package com.temple.web.cdi.session;

import com.temple.LocaleViewableTempleException;
import com.temple.service.ServiceException;
import com.temple.service.cdi.TempleObject;
import com.temple.service.cdi.session.LanguageBean;
import com.temple.view.LocaleStringView;
import com.temple.view.LocaleViewable;
import com.temple.web.cdi.application.AbstractApplicationBean;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.event.FacesEvent;
import javax.inject.Inject;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 * @see #serviceFailed(com.temple.service.ServiceException) to handle service calls exceptions
 */
public abstract class AbstractSessionBean extends AbstractApplicationBean {

	private static final long serialVersionUID = 1L;

	@Inject
	@TempleObject
	protected LanguageBean languageBean;

	private String componentId = null ;

	/**
	 * Sets the component id FacesMessages will be registered to
	 * 
	 * @param componentId an id
	 * @deprecated passing the event should be enough
	 */
	@Deprecated
	protected void setComponentId(String componentId) {
		this.componentId = componentId;
	}
	
	/**
	 * Adds a global info {@link FacesMessage}
	 *
	 * @param info the message content
	 */
	protected void addInfo(String info) {
		this.addInfo(null, info);
	}

	/**
	 * Adds an info {@link FacesMessage} to the component with the given id
	 *
	 * @param id a component id, null if the message is global
	 * @param info the message content
	 */
	protected void addInfo(String id, String info) {
		this.addMessage(id, FacesMessage.SEVERITY_INFO, info, null);
	}

	/**
	 * Adds a global warning {@link FacesMessage}
	 *
	 * @param warning the message content
	 */
	protected void addWarning(String warning) {
		this.addWarning(null, warning);
	}

	/**
	 * Adds a warning {@link FacesMessage} to the component with the given id
	 *
	 * @param id a component id, null if the message is global
	 * @param warning the message content
	 */
	protected void addWarning(String id, String warning) {
		this.addMessage(id, FacesMessage.SEVERITY_WARN, warning, null);
	}

	/**
	 * Adds a warning {@link FacesMessage} to the component with the given id
	 *
	 * @param id a component id
	 * @param lv the {@link LocaleViewable} the message content is to made from
	 */
	protected void addWarning(String id, LocaleViewable lv) {
		this.addWarning(id, this.languageBean.getString(lv));
	}
	
	/**
	 * Adds a global error {@link FacesMessage}
	 *
	 * @param error the message content
	 */
	protected void addError(String error) {
		this.addError(this.componentId, error);
	}

	/**
	 * Adds an error {@link FacesMessage} to the component with the given id
	 *
	 * @param id a component id, null if the message is global
	 * @param error the message content
	 */
	protected void addError(String id, String error) {
		this.addMessage(id, FacesMessage.SEVERITY_ERROR, error, null);
	}
	
	/**
	 * Adds a global error {@link FacesMessage} 
	 *
	 * @param e the exception which caused the error
	 */
	protected void addError(LocaleViewableTempleException e) {
		this.addError(this.componentId, e, true);
	}

	/**
	 * Adds an error {@link FacesMessage} to the component with the given id
	 *
	 * @param id a component id, null if the message is global
	 * @param e the exception which caused the error
	 */
	protected void addError(String id, LocaleViewableTempleException e) {
		this.addError(id, e, true);
	}

	private void addError(String id, LocaleViewableTempleException e, boolean logException) {
		if (logException) {
			this.thrown(e);
		}
		this.addMessage(id, FacesMessage.SEVERITY_ERROR, this.languageBean.getStringView(e));
	}

	/**
	 * Overrides parent behavior, instead adds an error message
	 * <br>
	 * TODOC
	 * @see AbstractApplicationBean#serviceFailed(com.temple.service.ServiceException, javax.faces.event.FacesEvent) 
	 */
	@Override
	protected void serviceFailed(final ServiceException e, FacesEvent fe) {
		this.addMessage(fe != null ? fe.getComponent().getClientId() : this.componentId, FacesMessage.SEVERITY_ERROR, this.languageBean.getStringView(e));
	}

	/**
	 * Adds a global fatal {@link FacesMessage}
	 *
	 * @param fatal the message content
	 */
	protected void addFatal(String fatal) {
		this.addFatal(null, fatal);
	}

	/**
	 * Adds a fatal {@link FacesMessage} to the component with the given id
	 *
	 * @param id a component id, null if the message is global
	 * @param fatal the message content
	 */
	protected void addFatal(String id, String fatal) {
		this.addMessage(id, FacesMessage.SEVERITY_FATAL, fatal, null);
	}

	/**
	 * Adds a global fatal {@link FacesMessage}
	 *
	 * @param e the exception which cause the fatal error.
	 */
	protected void addFatal(Exception e) {
		this.addFatal(e.getLocalizedMessage());
	}

	/**
	 * Adds a global fatal {@link FacesMessage}
	 *
	 * @param e the {@link LocaleViewableTempleException} which cause the fatal error.
	 */
	protected void addFatal(LocaleViewableTempleException e) {
		this.addMessage(null, FacesMessage.SEVERITY_FATAL, this.languageBean.getStringView(e));
	}

	private void addMessage(String id, Severity s, String l, String d) {
		FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(s, l, d));
	}

	private void addMessage(String id, Severity s, LocaleStringView slv) {
		this.addMessage(id, s, slv.getString(), slv.getDetailedString());
	}
}
