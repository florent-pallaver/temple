package com.temple.service.ejb.action;

import com.temple.model.access.AccessType;
import com.temple.view.LocaleViewable;

/**
 * TODOC
 * 
 * @author cr9z1k
 * @version 1.0
 * @param <AT> TODOC
 */
public interface ActionType<A extends AccessType> extends LocaleViewable {

	/**
	 * @return
	 */
	String key();

	/**
	 * @return
	 */
	PropertyDescription[] propertiesDescription();

	/**
	 * @return
	 */
	A getAccessType();
}
