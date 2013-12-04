package com.temple.ejb.action.constraint;

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
@Constraint(PatternConstraintChecker.class)
public @interface PatternConstraint {

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
	String pattern() ;
	
}
