package com.temple.web.cdi;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import com.temple.AbstractTempleBean;
import com.temple.LocalizedTempleException;
import com.temple.Module;
import com.temple.view.LocaleStringView;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class AbstractTempleWebBean extends AbstractTempleBean {

	/**
	 * Constructor.
	 */
	protected AbstractTempleWebBean() {
		super(Module.WEB);
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
