package com.temple.cdi.model;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;
import javax.validation.Constraint;
import javax.validation.Payload;

import com.temple.cdi.model.validation.AgeLimitValidator;

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
@Constraint(validatedBy = AgeLimitValidator.class)
public @interface AgeLimit {

	/**
	 * @return the minimum age limit included
	 */
	@Nonbinding
	byte min() default 0;

	/**
	 * @return the maximum age limit included
	 */
	@Nonbinding
	short max();

	@Nonbinding
	String message() default "{com.temple.model.validation.constraint.age}";

	@Nonbinding
	Class<?>[] groups() default {};

	@Nonbinding
	Class<? extends Payload>[] payload() default {};
}
