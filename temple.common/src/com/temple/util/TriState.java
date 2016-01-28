/**
 *
 */
package com.temple.util;

/**
 * TODOC
 * @author flominou
 */
public enum TriState {

	/**
	 * Equivalent to {@code false} or {@code 0}
	 */
	NONE(false),
	/**
	 * Equivalent to {@code null}
	 */
	BETWEEN(null),
	/**
	 * Equivalent to {@code true} or {@code 1}
	 */
	ALL(true);

	private final Boolean b;

	private final Integer i ;

	private TriState(Boolean b) {
		this.b = b;
		this.i = b == null ? null : Integer.valueOf(b ? 1 : 0) ;
	}

	/**
	 * @return
	 */
	public Boolean toBoolean() {
		return this.b;
	}

	/**
	 * @return
	 */
	public Integer toInteger() {
		return this.i ;
	}

}
