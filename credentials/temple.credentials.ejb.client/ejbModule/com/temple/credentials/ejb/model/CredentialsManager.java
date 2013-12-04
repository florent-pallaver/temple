package com.temple.credentials.ejb.model;

import java.io.Serializable;

import javax.ejb.Remote;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
@Remote
public interface CredentialsManager extends Serializable {

	/**
	 * TODOC
	 */
	String BEAN_NAME = "CredentialsBean";

	/**
	 * TODOC
	 */
	String BEAN_GLOBAL_JNDI_NAME = "java:global/temple.credentials/temple.credentials.ejb/" + CredentialsManager.BEAN_NAME;

	/**
	 * TODOC
	 * 
	 * @param login
	 * @param password
	 * @return
	 * @throws LoginNotFoundException
	 * @throws IncorrectPasswordException
	 */
	int findUserId(String login, String password) throws LoginNotFoundException, IncorrectPasswordException;

	/**
	 * TODOC
	 * 
	 * @param login
	 * @param password
	 * @param userId
	 * @throws SignUpException
	 */
	void createIdentity(String login, String password, int userId) throws CreateUserIdentityException;

	/**
	 * TODOC
	 * 
	 * @param userId
	 * @param current
	 * @param nevv
	 * @throws UpdateUserIdentityException
	 * @throws IncorrectPasswordException
	 */
	void updatePassword(int userId, String current, String nevv) throws UpdateUserIdentityException, IncorrectPasswordException;
	// reset password
}
