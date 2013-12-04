package com.temple.web.cdi;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * Qualifier for web application parameters.
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
public @interface WebApplicationParameter {

	/**
	 * @return the type of this application parameter is.
	 */
	Type value();

	/**
	 * TODOC
	 * 
	 * @author Florent Pallaver
	 * @version 1.0
	 */
	public static enum Type {
		CONTEXT_PATH,
	}
}
