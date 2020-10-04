package com.temple.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.temple.model.filter.FindMaxFilter;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface GenerateIntegerId {

	Class<? extends FindMaxFilter<Integer>> value();
}
