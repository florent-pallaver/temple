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
@Target(ElementType.FIELD)
@Constraint(LengthConstraintChecker.class)
public @interface LengthConstraint {

	/**
	 * 
	 * TODOC
	 * @return
	 */
	String[] value() ;

	/**
	 * 
	 * TODOC
	 * @return
	 */
	int min() default 0;
	
	/**
	 * 
	 * TODOC
	 * @return
	 */
	int max() default Integer.MAX_VALUE ;
	
}
