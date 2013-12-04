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
	 * @return the encrypted password
	 */
	String getEncryptedPassword();

	/**
	 * Sets the encrypted password
	 * 
	 * @param encryptedPassword - the encrypted password to set
	 */
	void setEncryptedPassword(String encryptedPassword);
}
