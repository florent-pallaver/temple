package com.temple.service.cdi;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER })
public @interface ImplementationStrategy {

	/**
	 * TODOC
	 */
	String FFMEG = "ffmpeg";

	/**
	 * TODOC
	 *
	 * @return
	 */
	String value();
}
