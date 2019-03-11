package com.temple.util.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

abstract class AbstractUDCryptAlgorithm implements UDCryptAlgorithm {

	private final MessageDigest md;

	AbstractUDCryptAlgorithm(String algo) {
		try {
			this.md = MessageDigest.getInstance(algo);
		} catch (final NoSuchAlgorithmException e) {
			throw new Error(algo + " is not available in this environment", e);
		}
	}

	@Override
	public final synchronized byte[] encrypt(byte[]... bytes) {
		for (final byte[] bs : bytes) {
			this.md.update(bs);
		}
		return this.md.digest();
	}

	@Override
	public final synchronized byte[] encrypt(byte[] bytes) {
		return this.md.digest(bytes);
	}

}
