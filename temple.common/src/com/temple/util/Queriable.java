package com.temple.util;

import java.util.Collection;

/**
 * @author Florent
 */
public interface Queriable<C> {

	/**
	 * TODOC
	 * @param query
	 * @return
	 */
	Collection<C> findAll(String query);

	/**
	 * TODOC
	 * @param query
	 * @return
	 */
	C find(String query);
}
