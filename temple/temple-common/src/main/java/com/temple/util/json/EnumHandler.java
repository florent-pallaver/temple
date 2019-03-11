package com.temple.util.json;

import java.util.HashMap;
import java.util.Map;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public final class EnumHandler<E extends Enum<E>> extends AbstractHandler {

	private static final Map<Class<?>, EnumHandler<?>> cache = new HashMap<>();

	private final Class<E> enumClass;

	private EnumHandler(Class<E> enumClass) {
		this.enumClass = enumClass;
	}

	@Override
	protected void nullSafeAdd(JsonObjectBuilder job, String name, Object value) {
		job.add(name, ((Enum<?>) value).name());
	}

	@Override
	protected void nullSafeAdd(JsonArrayBuilder jab, Object value) {
		jab.add(((Enum<?>) value).name());
	}

	@Override
	protected Object getNullSafeValue(JsonObject jo, String name) {
		return Enum.valueOf(this.enumClass, jo.getString(name));
	}

	/**
	 * TODOC
	 *
	 * @param enumClass
	 * @return
	 */
	public static final <E extends Enum<E>> EnumHandler<E> getInstance(Class<E> enumClass) {
		@SuppressWarnings("unchecked")
		EnumHandler<E> eh = (EnumHandler<E>) EnumHandler.cache.get(enumClass);
		if (eh == null) {
			eh = new EnumHandler<E>(enumClass);
		}
		return eh;
	}

}
