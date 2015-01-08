package com.temple.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.temple.model.filter.FindMaxFilter;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface GenerateIntegerId {

	/**
	 * TODOC
	 *
	 * @return
	 */
	Class<? extends FindMaxFilter<Integer>> value();
}
