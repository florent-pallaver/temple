/**
 *
 */
package com.temple.util.security;

import com.temple.util.TempleUtil;

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
	byte[] decrypt(byte[] s);

	/**
	 *
	 * @param s
	 * @return
	 */
	default byte[] decrypt64(String s64) {
		return this.decrypt(TempleUtil.base64Decode(s64));
	}
}
