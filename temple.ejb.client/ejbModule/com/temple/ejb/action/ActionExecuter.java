package com.temple.ejb.action;

import java.util.Collection;

import com.temple.LocalizedTempleException;
import com.temple.model.access.AccessType;
import com.temple.view.View;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @param <A>
 * @version 1.0
 */
public interface ActionExecuter<A extends AccessType> {

	Collection<Class<? extends ActionType<A>>> getExecutableTypes();

	/**
	 * Tries to executes an action with the {@link #getSessionUser() current session user}'s privilege.
	 * 
	 * @param action - an {@link Action} to execute.
	 * @return the executed action result.
	 * @throws LocalizedException if any error occurs while executing the given {@link Action}.
	 */
	View execute(Action<? extends ActionType<A>> a) throws LocalizedTempleException;
}
