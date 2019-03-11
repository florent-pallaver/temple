package com.temple.service.ejb.session;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.temple.AbstractTempleBean;
import com.temple.Module;
import com.temple.model.TempleUser;
import com.temple.service.cdi.TempleObject;
import com.temple.service.cdi.session.SessionBean;
import com.temple.service.ejb.model.TempleEntityManager;
import com.temple.service.session.AccessDeniedException;
import com.temple.service.session.NotSignedInException;
import com.temple.service.session.SessionException;
import com.temple.service.session.SessionManager;
import com.temple.service.session.SignInException;

/**
 * Should be in temple.credentials ...
 *
 * @author flominou
 */
@Stateless
@Local(SessionManager.class)
@Deprecated
public class SessionManagerBean extends AbstractTempleBean implements SessionManager {

	@EJB
	private TempleEntityManager em;

	// @EJB(lookup = CredentialsManager.BEAN_GLOBAL_JNDI_NAME)
	// private CredentialsManager cm;

	// @Inject
	// @CDIApplicationParameter(Type.USER_CLASS)
//	private ClassWrapper<TempleUser> userClass;

	@Inject
	@TempleObject
	private SessionBean currentSession;

	protected SessionManagerBean() {
		super(Module.SERVICE);
	}

	@Override
	public void signIn(String login, String password) throws SignInException {
		// try {
		// final int uid = this.cm.findUserId(login, password);
		// final TempleUser u =
		// this.em.findById(this.userClass.getWrappedClass(), uid);
		// if (u == null) {
		// throw new UserNotFoundException(login);
		// }
		// this.currentSession.setUser(u);
		// } catch (final FindEntityException | LoginNotFoundException e) {
		// throw new UserNotFoundException(login, e);
		// } catch (final IncorrectPassException e) {
		// throw new com.temple.service.session.IncorrectPassException(e);
		// }
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
	public void checkOwnership(Integer ownerId) throws SessionException {
		if (!this.getSessionUser().getId().equals(ownerId)) {
			throw new AccessDeniedException();
		}
	}

	@Override
	public TempleUser getSessionUser() throws NotSignedInException {
		final TempleUser user = this.currentSession.getUser();
		if (user == null) {
			throw new NotSignedInException();
		}
		return user;
	}
}
