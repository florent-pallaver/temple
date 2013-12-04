package com.temple.ejb.impl.session;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.temple.Module;
import com.temple.bean.AbstractTempleBean;
import com.temple.cdi.CDIApplicationParameter;
import com.temple.cdi.CDIApplicationParameter.Type;
import com.temple.cdi.util.ClassWrapper;
import com.temple.credentials.ejb.model.CredentialsManager;
import com.temple.credentials.ejb.model.IncorrectPasswordException;
import com.temple.credentials.ejb.model.LoginNotFoundException;
import com.temple.ejb.model.FindEntityException;
import com.temple.ejb.model.TempleEntityManager;
import com.temple.ejb.session.AccessDeniedException;
import com.temple.ejb.session.NoUserSessionException;
import com.temple.ejb.session.SessionException;
import com.temple.ejb.session.SessionManager;
import com.temple.ejb.session.SignInException;
import com.temple.ejb.session.UserNotFoundException;
import com.temple.model.TempleUser;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
@Stateless
@Local(SessionManager.class)
public class SessionManagerBean extends AbstractTempleBean implements SessionManager {

	@EJB
	private TempleEntityManager em;

	@EJB(lookup = CredentialsManager.BEAN_GLOBAL_JNDI_NAME)
	private CredentialsManager cm;

	@Inject
	@CDIApplicationParameter(Type.USER_CLASS)
	private ClassWrapper<TempleUser> userClass;

	@Inject
	private SessionData currentSession;

	SessionManagerBean() {
		super(Module.EJB);
	}

	@Override
	public void signIn(String login, String password) throws SignInException {
		try {
			final int uid = this.cm.findUserId(login, password);
			final TempleUser u = this.em.findById(this.userClass.getWrappedClass(), Integer.valueOf(uid));
			if (u == null) {
				throw new UserNotFoundException(login);
			}
			this.currentSession.setUser(u);
		} catch (final FindEntityException | LoginNotFoundException e) {
			throw new UserNotFoundException(login, e);
		} catch (final IncorrectPasswordException e) {
			throw new com.temple.ejb.session.IncorrectPasswordException(e);
		}
	}

	@Override
	public void signOut() {
		this.currentSession.setUser(null);
	}

	@Override
	public boolean isSignedIn() {
		return this.currentSession.isSignedIn();
	}

	@Override
	public void checkSession() throws NoUserSessionException {
		this.getSessionUser();
	}

	@Override
	public void checkOwnership(Integer ownerId) throws SessionException {
		if (!this.getSessionUser().getId().equals(ownerId)) {
			throw new AccessDeniedException();
		}
	}

	@Override
	public TempleUser getSessionUser() throws NoUserSessionException {
		final TempleUser user = this.currentSession.getUser();
		if (user == null) {
			throw new NoUserSessionException();
		}
		return user;
	}

	@Override
	public Object getParameter(String key) throws SessionException {
		return this.currentSession.get(key);
	}

	@Override
	public void setParameter(String key, Object value) throws SessionException {
		this.currentSession.set(key, value);
	}
}
