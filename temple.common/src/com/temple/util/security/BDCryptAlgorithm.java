/**
 * 
 */
package com.temple.util.security;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 */
public interface BDCryptAlgorithm extends UDCryptAlgorithm {

	/**
	 * @param s
	 * @return
	 */
	String decrypt(String s);
}
