package com.temple.model.access;

import com.temple.view.LocaleViewable;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public interface AccessRights<A extends AccessType, R extends Realm> extends LocaleViewable, Cloneable {

	/**
	 * TODOC
	 * 
	 * @param r - a {@link Realm}
	 * @param a - an {@link AccessType}
	 * @param allow - <code>true</code> to allow the given {@link AccessType} within the given {@link Realm}
	 */
	void allow(R r, A a, boolean allow);

	/**
	 * TODOC
	 * 
	 * @param r - a {@link Realm}
	 * @param a - an {@link AccessType}
	 * @return
	 */
	boolean allows(R r, A a);

	/**
	 * TODOC
	 * 
	 * @param a - an {@link AccessType}
	 * @return
	 */
	boolean allowsForAll(A a);
}
