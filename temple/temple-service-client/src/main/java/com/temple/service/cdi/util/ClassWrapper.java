package com.temple.service.cdi.util;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public class ClassWrapper<X> {

	private Class<? extends X> wrappedClass;

	ClassWrapper() {
		super();
	}

	public ClassWrapper(Class<? extends X> c) {
		this();
		this.wrappedClass = c;
	}

	/**
	 * @return the wrappedClass
	 */
	public Class<? extends X> getWrappedClass() {
		return this.wrappedClass;
	}
}
