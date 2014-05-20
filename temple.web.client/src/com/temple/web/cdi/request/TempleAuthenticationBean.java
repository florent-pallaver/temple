package com.temple.web.cdi.request;

import java.io.IOException;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.temple.cdi.TempleBean;
import com.temple.cdi.request.AuthenticationBean;
import com.temple.ejb.session.SessionManager;
import com.temple.ejb.session.SignInException;

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
public class TempleAuthenticationBean extends AbstractRequestBean implements AuthenticationBean {

	@EJB
	private SessionManager sessionManager;

	private String login;

	private String password;

	@Override
	public void signIn() {
		try {
			this.sessionManager.signIn(this.login, this.password);
		} catch (final SignInException e) {
			this.addErrorMessage(e);
		}
	}

	@Override
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

	@Override
	public boolean isSignedIn() {
		return this.sessionManager.isSignedIn();
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return this.login;
	}

	/**
	 * Sets the login
	 * 
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Sets the password
	 * 
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
