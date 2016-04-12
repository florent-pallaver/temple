/**
 *
 */
package com.temple.util.security;

import java.util.Random;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 * @see MD5CryptAlgorithm
 * @see SHA512Base64CryptAlgorithm
 */
// WARN SecureRandom can be very slow !!
public final class Security {

	private Security() {
	}

	/**
	 * TODOC
	 *
	 * @param length
	 * @return
	 */
	public static byte[] randomBytes(int length) {
		final byte[] bytes = new byte[length];
		final Random random = new Random();
		random.nextBytes(bytes);
		return bytes;
	}

	/**
	 * TODOC
	 *
	 * @param length
	 * @return
	 */
	public static char[] randomChars(int length) {
		final char[] chars = new char[length];
		final Random r = new Random() ;
		for (int i = length; i-- > 0;) {
			chars[i] = (char) r.nextInt(Character.MAX_VALUE);
		}
		return chars;
	}

	/**
	 * Implementation of {@link UDCryptAlgorithm} to crypt using
	 * {@value #algorithm}.
	 *
	 * @author Florent Pallaver
	 * @version 1.0
	 * @see #instance
	 */
	public static final class MD5CryptAlgorithm extends AbstractUDCryptAlgorithm {

		/**
		 * Sole instance of this class
		 */
		public static final UDCryptAlgorithm instance = new MD5CryptAlgorithm();

		/**
		 * The algorithm used by this class to crypt string.
		 */
		public static final String algorithm = "MD5";

		private MD5CryptAlgorithm() {
			super(MD5CryptAlgorithm.algorithm);
		}

	}

	/**
	 * Implementation of {@link UDCryptAlgorithm} to crypt using
	 * {@value #algorithm}.
	 *
	 * @author Florent Pallaver
	 * @version 1.0
	 * @see #instance
	 */
	public static final class SHA512CryptAlgorithm extends AbstractUDCryptAlgorithm {

		/**
		 * Sole instance of this class
		 */
		public static final UDCryptAlgorithm instance = new SHA512CryptAlgorithm();

		/**
		 * The algorithm used by this class to crypt string.
		 */
		public static final String algorithm = "SHA-512";

		private SHA512CryptAlgorithm() {
			super(SHA512CryptAlgorithm.algorithm);
		}

	}

}
