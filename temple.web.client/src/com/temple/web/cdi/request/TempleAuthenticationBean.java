package com.temple.web.cdi.request;

import java.io.IOException;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.temple.service.cdi.TempleBean;
import com.temple.service.session.SessionManager;
import com.temple.service.session.SignInException;

/**
 * TODOC
 * Bean to use to register any data relative to the current session
 *
 * @author Florent Pallaver
 * @version 1.0
 */
@Named("authenticationBean")
@Model
@TempleBean
public class TempleAuthenticationBean extends AbstractRequestBean {

	private static final long serialVersionUID = 1L;

	@EJB
	private SessionManager sessionManager;

	protected String name;

	protected String pass;

	public void signIn() {
		try {
			this.sessionManager.signIn(this.name, this.pass);
		} catch (final SignInException e) {
			this.addErrorMessage(e);
		}
	}

	public void signOut() {
		this.sessionManager.signOut();
		final ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.invalidateSession();
		try {
			ec.redirect(ec.getApplicationContextPath() + "/");
		} catch (final IOException e) {
			this.addError(e);
		}
	}

	public boolean isSignedIn() {
		return this.sessionManager.isSignedIn();
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return this.name;
	}

	/**
	 * Sets the login
	 *
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.name = login;
	}

	/**
	 * @return the pass
	 */
	public String getPass() {
		return this.pass;
	}

	/**
	 * Sets the pass
	 *
	 * @param pass the pass to set
	 */
	public void setPass(String password) {
		this.pass = password;
	}
}
