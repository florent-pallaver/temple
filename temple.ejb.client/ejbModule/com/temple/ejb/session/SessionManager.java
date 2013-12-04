package com.temple.ejb.session;

import com.temple.model.TempleUser;

/**
 * interface to manage user sessions..
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public interface SessionManager {

	/**
	 * Signs in a user and associates it to the current session thanks to its login and password.
	 * 
	 * @param login - a login.
	 * @param password - a password.
	 * @throws SignInException whenever the sign in process fails.
	 */
	void signIn(String login, String password) throws SignInException;

	/**
	 * Signs out the user associated to this current session.
	 */
	void signOut();

	/**
	 * Checks that the current session has a {@link TempleUser} attached to it.
	 * 
	 * @throws NoUserSessionException if no {@link TempleUser} is attached to the current session.
	 */
	@Deprecated
	void checkSession() throws NoUserSessionException;

	/**
	 * TODOC
	 * 
	 * @param ownerId
	 * @throws SessionException
	 */
	void checkOwnership(Integer ownerId) throws SessionException;

	/**
	 * @return <code>true</code> if the current session has a signed in user, <code>false</code> otherwise.
	 */
	boolean isSignedIn();

	/**
	 * TODOC
	 * 
	 * @throws NoUserSessionException
	 */
	TempleUser getSessionUser() throws NoUserSessionException;

	/**
	 * TODOC
	 * 
	 * @param key
	 * @return
	 * @throws SessionException
	 */
	Object getParameter(String key) throws SessionException;

	/**
	 * TODOC
	 * 
	 * @param sid
	 * @param value
	 * @throws SessionException
	 */
	void setParameter(String key, Object value) throws SessionException;
}
