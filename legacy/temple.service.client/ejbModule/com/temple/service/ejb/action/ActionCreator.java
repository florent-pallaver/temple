package com.temple.service.ejb.action;

import java.util.Collection;

import com.temple.LocalizedTempleException;
import com.temple.model.access.AccessType;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @param <A>
 * @version 1.0
 */
public interface ActionCreator<A extends AccessType> {

	/**
	 * TODOC
	 * 
	 * @return
	 */
	Collection<Class<? extends ActionType<A>>> getCreatableTypes();

	/**
	 * Creates a new {@link Action} that has the given {@link ActionType} with <em>uninitialized</em>
	 * {@link Action#getProperties() properties}.
	 * 
	 * @param at - an {@link ActionType}.
	 * @return a newly created {@link Action}.
	 * @throws LocalizedTempleException
	 */
	<T extends ActionType<A>> Action<T> createAction(T at) throws LocalizedTempleException;
}
