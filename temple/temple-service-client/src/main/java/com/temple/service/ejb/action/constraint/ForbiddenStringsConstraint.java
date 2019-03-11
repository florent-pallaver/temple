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
@Constraint(ForbiddenStringsConstraintChecker.class)
public @interface ForbiddenStringsConstraint {

	/**
	 * 
	 * TODOC
	 * @return
	 */
	String[] value() ;

	/**
	 * The forbidden Strings.
	 */
	String[] forbidden() ;
	
}
