package com.temple.util;

/**
 * Base contract for an object containing a value.
 * 
 * @author cr9z1k
 * @version 1.0
 * @param <R> - the type of the value contained in this object.
 */
public interface Valuable<R> {

	/**
	 * @return the value of this object.
	 */
	R getValue();

	/**
	 * Sets the value of this object.
	 * 
	 * @param value - the value to set.
	 */
	void setValue(R value);

}
