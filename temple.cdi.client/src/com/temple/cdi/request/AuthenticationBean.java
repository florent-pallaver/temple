package com.temple.cdi.request;


/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public interface AuthenticationBean {

	/**
	 * TODOC
	 */
	public void signIn();

	/**
	 * TODOC
	 */
	public void signOut();

	/**
	 * @return <code>true</code> if the current session has a signed in user, <code>false</code> otherwise.
	 */
	public boolean isSignedIn();
}
