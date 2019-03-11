package com.temple.service.cdi;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.inject.Qualifier;

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER })
public @interface ImplementationStrategy {

	/**
	 * Implementation based on the <strong>ffmpeg</strong> program
	 */
	String FFMEG = "ffmpeg";

	/**
	 * Implementation based on the <strong>file</strong> program
	 */
	String FILE = "file" ;
	
	/**
	 * Implementation based on <a href="http://geonames.org/"><strong>geonames.org</strong></a>
	 */
	String GEONAMES = "geonames" ;
	
	String value();
}
