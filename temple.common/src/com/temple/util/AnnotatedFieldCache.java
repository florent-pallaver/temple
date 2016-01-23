package com.temple.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public final class AnnotatedFieldCache<A extends Annotation> extends AbstractLogger {

	private final Map<Class<?>, AnnotatedField<A>[]> cache = new HashMap<>();

	private final Class<A> annotationClass;

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param annotationClass
	 */
	public AnnotatedFieldCache(Class<A> annotationClass) {
		super(annotationClass.getName());
		this.annotationClass = annotationClass;
	}

	/**
	 * TODOC
	 * 
	 * @param c
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public AnnotatedField<A>[] getFields(Class<?> c) {
		AnnotatedField<A>[] toStringFields = this.cache.get(c);
		if (toStringFields == null) {
			if (this.isDebugLoggable()) {
				this.debug("Finding annotated fields in " + c);
			}
			final List<AnnotatedField<A>> fields = new ArrayList<>();
			for (final Field f : c.getDeclaredFields()) {
				final A a = f.getAnnotation(this.annotationClass);
				if (a != null) {
					fields.add(new AnnotatedField<>(a, f));
				}
			}
			final Class<?> sc = c.getSuperclass();
			if (sc != null) {
				fields.addAll(0, Arrays.asList(this.getFields(sc)));
			}
			toStringFields = fields.toArray(new AnnotatedField[fields.size()]);
			this.cache.put(c, toStringFields);
		}
		return toStringFields;
	}
}
