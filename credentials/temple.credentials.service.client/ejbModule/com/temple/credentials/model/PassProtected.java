/**
 *
 */
package com.temple.credentials.model;

/**
 * Base contract for an object to be pass protected.
 *
 * @author flominou
 */
public interface PassProtected {

	/**
	 * @return the salt used to hash the pass
	 */
	String getSalt();

	/**
	 * @return the pass' hash
	 */
	String getPassHash();

	/**
	 * Sets the pass' hash and a random salt
	 *
	 * @param pass
	 *            the pass to hash and set
	 */
	void setPass(String pass);

	/**
	 * Sets the salt and sets pass' hash
	 *
	 * @param pass
	 *            the pass to set
	 * @param salt
	 *            the salt used to hash the pass
	 */
	void setPass(String pass, String salt);

	/**
	 *
	 * @param pass
	 * @return TODOC
	 */
	boolean matchesPass(String pass) ;

}
