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
public interface UDCryptAlgorithm {

	/**
	 * @param s
	 * @return
	 */
	default byte[] encrypt(String s) {
		return this.encrypt(s.getBytes());
	}

	/**
	 *
	 * @param strings
	 * @return
	 */
	default byte[] encrypt(String... strings) {
		final byte[][] bytes = new byte[strings.length][];
		for (int i = strings.length; i-- > 0;) {
			bytes[i] = strings[i].getBytes();
		}
		return this.encrypt(bytes);
	}

	/**
	 *
	 * @param bytes
	 * @return
	 */
	default byte[] encrypt(byte[] bytes) {
		return this.encrypt(new byte[][]{bytes}) ;
	}

	/**
	 *
	 * @param bytes
	 * @return
	 */
	byte[] encrypt(byte[]... bytes);

	/**
	 *
	 * @param s
	 * @return
	 */
	default String encrypt64(String s) {
		return this.encrypt64(s.getBytes());
	}

	/**
	 *
	 * @param strings
	 * @return
	 */
	default String encrypt64(String... strings) {
		return TempleUtil.base64Encode(this.encrypt(strings));
	}

	/**
	 *
	 * @param bytes
	 * @return
	 */
	default String encrypt64(byte[] bytes) {
		return TempleUtil.base64Encode(this.encrypt(bytes));
	}

	/**
	 *
	 * @param bytes
	 * @return
	 */
	default String encrypt64(byte[]... bytes) {
		return TempleUtil.base64Encode(this.encrypt(bytes));
	}

}
