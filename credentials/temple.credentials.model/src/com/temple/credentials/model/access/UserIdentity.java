package com.temple.credentials.model.access;

import java.io.Serializable;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public interface UserIdentity extends Serializable {

	/**
	 * @return the login (or user name) used to authenticate the user linked to this identity.
	 */
	String getLogin();

	/**
	 * @return the id of the user linked to this identity
	 */
	int getUserId();

	/**
	 * @return the encrypted pass
	 */
	String getEncryptedPass();

	/**
	 * Sets the encrypted pass
	 *
	 * @param encryptedPass - the encrypted pass to set
	 */
	void setEncryptedPass(String encryptedPass);
}
