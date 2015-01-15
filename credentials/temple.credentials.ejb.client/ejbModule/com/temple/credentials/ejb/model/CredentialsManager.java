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
	 * @param pass
	 * @return
	 * @throws LoginNotFoundException
	 * @throws IncorrectPassException
	 */
	int findUserId(String login, String pass) throws LoginNotFoundException, IncorrectPassException;

	/**
	 * TODOC
	 *
	 * @param login
	 * @param pass
	 * @param userId
	 * @throws com.temple.credentials.ejb.model.CreateUserIdentityException
	 */
	void createIdentity(String login, String pass, int userId) throws CreateUserIdentityException;

	/**
	 * TODOC
	 *
	 * @param userId
	 * @param current
	 * @param nevv
	 * @throws UpdateUserIdentityException
	 * @throws IncorrectPassException
	 */
	void updatePass(Integer userId, String current, String nevv) throws UpdateUserIdentityException, IncorrectPassException;
	// TODO reset pass
}
