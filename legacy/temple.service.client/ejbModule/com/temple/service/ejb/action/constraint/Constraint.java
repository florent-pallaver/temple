package com.temple.service.ejb.action.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * TODOC 
 * @author cr9z1k
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Constraint {

	/**
	 * 
	 * TODOC
	 * @return
	 */
	Class<? extends PropertiesChecker<?>> value() ;
	
	/**
	 * 
	 * TODOC
	 * @return
	 */
	String keysProperty() default "value"	;
	
}
