package com.temple.util.security;

public enum KeySize {

	_128, _256, _512, _1024, _2048, _4096, _8192;

	private final int size;

	private KeySize() {
		this.size = 128 << this.ordinal();
	}

	/**
	 * @return the size
	 */
	public int getBitsCount() {
		return this.size;
	}

}