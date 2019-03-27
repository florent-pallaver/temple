package com.temple.service.ejb.action;

import java.io.Serializable;
import java.util.Map;

import com.temple.view.LocaleViewable;

/**
 * Base contract for an object to be used to perform an atomic action.
 * <p>
 * The {@link #getProperties() properties} of an action are used as its parameters to execute it.
 *
 * @author Florent Pallaver
 * @version 1.0
 * @see Property
 */
public interface Action<AT extends ActionType<?>> extends LocaleViewable {

	/**
	 * @return The {@link ActionType} of this action.
	 */
	AT getType();

	/**
	 * @return the properties of this action.
	 */
	Map<String, Property> getProperties();

	/**
	 * @return the id automatically generated by the {@link ApplicationRemoteEJB}
	 */
	Serializable getId();
}