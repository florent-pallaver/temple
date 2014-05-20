/**
 * 
 */
package com.temple.util.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import com.temple.util.TempleUtil;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 * @see MD5CryptAlgorithm
 * @see SHA512Base64CryptAlgorithm
 * @see NoCryptAlgorithm
 */
public final class Security {

	private Security() {}

	/**
	 * TODOC
	 * 
	 * @param length
	 * @return
	 */
	public static byte[] randomByteArray(int length) {
		final Random r = new Random();
		final byte[] bytes = new byte[length];
		for (int i = length; i-- > 0;) {
			bytes[i] = (byte) r.nextInt(Byte.MAX_VALUE);
		}
		return bytes;
	}

	/**
	 * TODOC
	 * 
	 * @param length
	 * @return
	 */
	public static char[] randomCharArray(int length) {
		final Random r = new Random();
		final char[] bytes = new char[length];
		for (int i = length; i-- > 0;) {
			bytes[i] = (char) r.nextInt(Character.MAX_VALUE);
		}
		return bytes;
	}

	/**
	 * Implementation of {@link UDCryptAlgorithm} to crypt a String using {@value #algorithm}.
	 * 
	 * @author Florent Pallaver
	 * @version 1.0
	 * @see #instance
	 */
	public static final class MD5CryptAlgorithm implements UDCryptAlgorithm {

		/**
		 * Sole instance of this class
		 */
		public static final UDCryptAlgorithm instance = new MD5CryptAlgorithm();

		/**
		 * The algorithm used by this class to crypt string.
		 */
		public static final String algorithm = "MD5";

		private final MessageDigest md;

		private MD5CryptAlgorithm() {
			try {
				this.md = MessageDigest.getInstance(MD5CryptAlgorithm.algorithm);
			} catch (final NoSuchAlgorithmException e) {
				throw new Error(MD5CryptAlgorithm.algorithm + " is not available in this environment", e);
			}
		}

		@Override
		public String encrypt(String s) {
			return new String(this.md.digest(s.getBytes()));
		}
	}

	/**
	 * Implementation of {@link UDCryptAlgorithm} to crypt a String using {@value #algorithm} and return it
	 * base64-encoded.
	 * 
	 * @author Florent Pallaver
	 * @version 1.0
	 * @see #instance
	 */
	public static final class SHA512Base64CryptAlgorithm implements UDCryptAlgorithm {

		/**
		 * Sole instance of this class
		 */
		public static final UDCryptAlgorithm instance = new SHA512Base64CryptAlgorithm();

		/**
		 * The algorithm used by this class to crypt string.
		 */
		public static final String algorithm = "SHA-512";

		private final MessageDigest md;

		private SHA512Base64CryptAlgorithm() {
			try {
				this.md = MessageDigest.getInstance(SHA512Base64CryptAlgorithm.algorithm);
			} catch (final NoSuchAlgorithmException e) {
				throw new Error(SHA512Base64CryptAlgorithm.algorithm + " is not available in this environment", e);
			}
		}

		@Override
		public String encrypt(String s) {
			return TempleUtil.base64Encode(this.md.digest(s.getBytes()));
		}
	}

	/**
	 * TODOC
	 * 
	 * @author Florent Pallaver
	 * @version 1.0
	 * @see #instance
	 */
	public static final class NoCryptAlgorithm implements BDCryptAlgorithm {

		/**
		 * Sole instance of this class.
		 */
		public static final BDCryptAlgorithm instance = new NoCryptAlgorithm();

		private NoCryptAlgorithm() {}

		@Override
		public String encrypt(String s) {
			return s;
		}

		@Override
		public String decrypt(String s) {
			return s;
		}
	}
}
