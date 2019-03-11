package com.temple.util.security;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * @author flominou
 */
public class AESBDCryptAlgorithm implements BDCryptAlgorithm {

	private static final String algorithm = "AES/ECB/PKCS5Padding";

	private final SecretKey key ;

	private final Cipher cipher;

	/**
	 * Constructor.
	 */
	public AESBDCryptAlgorithm(KeySize keySize) {
		try {
			final KeyGenerator kg = KeyGenerator.getInstance("AES");
			kg.init(KeySize._128.getBitsCount()) ;
			this.key = kg.generateKey() ;
			this.cipher = Cipher.getInstance(AESBDCryptAlgorithm.algorithm) ;
		} catch (final NoSuchAlgorithmException e) {
			throw new Error("Algorithm not found ...", e);
		} catch (final NoSuchPaddingException e) {
			throw new Error("Padding not found ...", e);
		}
	}

	@Override
	public synchronized byte[] encrypt(byte[] bytes) {
		try {
			this.cipher.init(Cipher.ENCRYPT_MODE, this.key);
			return this.cipher.doFinal(bytes) ;
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			throw new RuntimeException(e) ;
		}
	}

	@Override
	public synchronized byte[] encrypt(byte[]... bytes) {
		try {
			this.cipher.init(Cipher.ENCRYPT_MODE, this.key);
			for (final byte[] bs : bytes) {
				this.cipher.update(bs) ;
			}
			return this.cipher.doFinal() ;
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			throw new RuntimeException(e) ;
		}
	}

	@Override
	public synchronized byte[] decrypt(byte[] bytes) {
		try {
			this.cipher.init(Cipher.DECRYPT_MODE, this.key);
			return this.cipher.doFinal(bytes) ;
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			throw new RuntimeException(e) ;
		}
	}

}
