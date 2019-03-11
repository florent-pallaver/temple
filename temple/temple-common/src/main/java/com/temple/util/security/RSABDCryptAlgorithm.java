package com.temple.util.security;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * TODOC
 *
 * @author flominou
 */
public class RSABDCryptAlgorithm implements BDCryptAlgorithm {

	private static final String algorithm = "RSA/ECB/PKCS1Padding";

	private final KeySize keySize;

	private final KeyPair pair;

	private final Cipher cipher;

	/**
	 * Constructor <br>
	 * Uses {@link KeySize#_1024}
	 */
	public RSABDCryptAlgorithm() {
		this(KeySize._1024);
	}

	/**
	 * Constructor
	 *
	 * @param keySize
	 *            the key size to use (at least 512 bits)
	 */
	public RSABDCryptAlgorithm(KeySize keySize) {
		this.keySize = keySize;
		try {
			final KeyPairGenerator instance = KeyPairGenerator.getInstance("RSA");
			instance.initialize(keySize.getBitsCount());
			this.pair = instance.generateKeyPair();
			this.cipher = Cipher.getInstance(RSABDCryptAlgorithm.algorithm);
		} catch (final NoSuchAlgorithmException e) {
			throw new Error("Algorithm not found ...", e);
		} catch (final NoSuchPaddingException e) {
			throw new Error("Padding not found ...", e);
		}
	}

	private byte[] crypt(byte[] bytes, int overhead) throws IllegalBlockSizeException, BadPaddingException {
		byte[] toReturn = new byte[0];

		final int blockLength = Math.min(bytes.length, (this.keySize.getBitsCount() >> 3) - overhead);

		final int remaining = bytes.length % blockLength;
		final int l = bytes.length - remaining;
		for (int i = 0; i < l; i += blockLength) {
			toReturn = this.append(toReturn, this.cipher.doFinal(bytes, i, blockLength));
		}
		if (remaining > 0) {
			toReturn = this.append(toReturn, this.cipher.doFinal(bytes, l, remaining));
		}
		return toReturn;
	}

	private byte[] append(byte[] prefix, byte[] suffix) {
		final byte[] toReturn = new byte[prefix.length + suffix.length];
		System.arraycopy(prefix, 0, toReturn, 0, prefix.length);
		System.arraycopy(suffix, 0, toReturn, prefix.length, suffix.length);
		return toReturn;
	}

	private byte[] append(byte[]... bytes) {
		int count = 0;
		for (final byte[] b : bytes) {
			count += b.length;
		}
		final byte[] toReturn = new byte[count];
		count = 0;
		for (final byte[] b : bytes) {
			System.arraycopy(b, 0, toReturn, count, b.length);
			count += b.length;
		}
		return toReturn;
	}

	@Override
	public synchronized byte[] encrypt(byte[]... bytes) {
		try {
			this.cipher.init(Cipher.ENCRYPT_MODE, this.pair.getPublic());
			return this.crypt(this.append(bytes), 11);
		} catch (final InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public synchronized byte[] decrypt(byte[] bytes) {
		try {
			this.cipher.init(Cipher.DECRYPT_MODE, this.pair.getPrivate());
			return this.crypt(bytes, 0);
		} catch (final BadPaddingException | InvalidKeyException | IllegalBlockSizeException e) {
			throw new RuntimeException(e);
		}
	}

}
