package com.temple.service.cdi.util;

import java.io.Serializable;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public class ObjectWrapper<X> implements Serializable {

	private static final long serialVersionUID = 1L;

	private X wrappedObject;

	ObjectWrapper() {}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param object
	 */
	public ObjectWrapper(X object) {
		super();
		this.wrappedObject = object;
	}

	/**
	 * @return the wrappedObject
	 */
	public X getWrappedObject() {
		return this.wrappedObject;
	}
}
