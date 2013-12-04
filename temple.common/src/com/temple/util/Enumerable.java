/**
 * 
 */
package com.temple.util;

import java.io.Serializable;

/**
 * Base contract for an object to be usable like an {@link Enum}..
 * 
 * @author Florent
 */
public interface Enumerable extends Serializable {

	/**
	 * @return the {@link Enum} equivalent of this object.
	 */
	Enum<?> toEnum();
}
