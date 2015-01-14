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
@Named("AuthenticationBean")
@Model
@TempleBean
public class TempleAuthenticationBean extends AbstractRequestBean implements AuthenticationBean {

	private static final long serialVersionUID = 1L;

	@EJB
	private SessionManager sessionManager;

	protected String name;

	protected String pass;

	@Override
	public void signIn() {
		try {
			this.sessionManager.signIn(this.name, this.pass);
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
