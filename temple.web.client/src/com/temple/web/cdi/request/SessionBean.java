package com.temple.web.cdi.request;

import javax.ejb.EJB;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.temple.cdi.TempleBean;
import com.temple.ejb.session.NoUserSessionException;
import com.temple.ejb.session.SessionManager;
import com.temple.ejb.session.SignInException;
import com.temple.model.TempleUser;
import com.temple.web.cdi.WebRequestParameter;
import com.temple.web.cdi.WebRequestParameter.Type;

/**
 * TODOC
 * Bean to use to register any data relative to the current session
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
@Model
@TempleBean
public class SessionBean extends AbstractRequestBean {

	@EJB
	private SessionManager sessionManager;

	private String login;

	private String password;

	@Inject
	@WebRequestParameter(type = Type.PAGE)
	private Instance<String> currentpage;

	/**
	 * TODOC
	 * 
	 * @throws SignInException
	 */
	public void signIn() throws SignInException {
		try {
			this.sessionManager.signIn(this.login, this.password);
		} catch (final SignInException e) {
			this.addErrorMessage(e);
		}
	}

	/**
	 * TODOC
	 */
	public void signOut() {
		this.sessionManager.signOut();
	}

	/**
	 * @return <code>true</code> if the current session has a signed in user, <code>false</code> otherwise.
	 */
	public boolean isSignedIn() {
		return this.sessionManager.isSignedIn();
	}

	/**
	 * TODOC
	 * 
	 * @return
	 */
	public TempleUser getSessionUser() {
		try {
			return this.sessionManager.getSessionUser();
		} catch (final NoUserSessionException e) {
			this.addErrorMessage(e);
			return null;
		}
	}

	/**
	 * TODOC
	 * 
	 * @return
	 */
	public String getSessionLogin() {
		final TempleUser u = this.getSessionUser();
		return u != null ? u.getLogin() : null;
	}

	/**
	 * @return the current page
	 */
	public String getCurrentPage() {
		return this.currentpage.get();
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
