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
@Constraint(RangeConstraintChecker.class)
public @interface RangeConstraint {

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
	int min() default Integer.MIN_VALUE;
	
	/**
	 * 
	 * TODOC
	 * @return
	 */
	int max() default Integer.MAX_VALUE ;

	
}
