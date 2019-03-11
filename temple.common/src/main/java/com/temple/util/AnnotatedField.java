package com.temple.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public class AnnotatedField<A extends Annotation> {

	public final A annotation;

	public final Field field;

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param annotation
	 * @param field
	 */
	public AnnotatedField(A annotation, Field field) {
		super();
		this.annotation = annotation;
		this.field = field;
	}
}
