package com.temple.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TODOC
 * 
 * @author Florent
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ToString {
	// boolean withFieldName() default true;
	
	/**
	 * @return whether to render this field if it is <code>null</code>, default to <code>false</code>
	 */
	boolean renderIfNull() default false ;
}
