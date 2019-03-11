package com.temple.web.cdi;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;

/**
 * Qualifier for request parameters.
 *
 * @author Florent Pallaver
 * @version 1.0
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
public @interface WebRequestParameter {

	/**
	 * @return the index of the request parameter, default to 0.
	 */
	@Nonbinding
	int index() default 0;

	/**
	 * @return the type of this request parameter is.
	 */
	Type type() default Type.STRING;

	/**
	 * @return the key of the request parameter, overrides index if both are defined.
	 */
	@Nonbinding
	String key() default "";

	/**
	 * TODOC
	 *
	 * @author Florent Pallaver
	 * @version 1.0
	 */
	public static enum Type {
		PAGE,
		STRING,
		INTEGER,
		/**
		 * To be <code>true</code> the parameter just need to exists, <code>false</code> otherwise.
		 */
		BOOLEAN;
	}
}
