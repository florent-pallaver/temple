package com.temple.util.json;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import com.temple.util.AnnotatedField;
import com.temple.util.AnnotatedFieldCache;
import com.temple.util.TempleUtil;
import com.temple.util.json.JsonField.Handler;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class JsonUtil extends TempleUtil {

	private static final Map<Class<? extends Handler>, Handler> handlerCache = new HashMap<>();

	/**
	 * TODOC
	 */
	protected static final AnnotatedFieldCache<JsonField> jsonFieldsCache = new AnnotatedFieldCache<>(JsonField.class);

	/**
	 * Constructor.
	 */
	protected JsonUtil() {
	}

	/**
	 * TODOC
	 *
	 * @param j
	 * @return
	 */
	public static final JsonObject toJsonObject(Object j) {
		return JsonUtil.toJsonObjectBuilder(j).build();
	}

	private static final JsonObjectBuilder toJsonObjectBuilder(Object o) {
		final JsonObjectBuilder job = Json.createObjectBuilder();
		JsonUtil.fieldsStream(o).parallel().filter(f -> f.annotation.outputable())
				.forEach(f -> JsonUtil.getHandler(f).add(job, f.field.getName(), TempleUtil.get(o, f.field)));
		return job;
	}

	/**
	 *
	 * @param j
	 * @return
	 */
	public static final JsonArray toJsonArray(Object j) {
		final JsonArrayBuilder jab = Json.createArrayBuilder();
		JsonUtil.fieldsStream(j).filter(f -> f.annotation.outputable()).forEach(f -> JsonUtil.getHandler(f).add(jab, TempleUtil.get(j, f.field)));
		return jab.build();
	}

	private static final Stream<AnnotatedField<JsonField>> fieldsStream(Object o) {
		return Arrays.stream(JsonUtil.jsonFieldsCache.getFields(o.getClass()));
	}

	/**
	 *
	 * @param c
	 * @return
	 */
	public static final JsonArray toJsonArray(final Object[] objects) {
		return JsonUtil.toJsonArray(Arrays.asList(objects), false);
	}

	/**
	 *
	 * @param c
	 * @return
	 */
	public static final JsonArray toJsonArray(Collection<?> c) {
		return JsonUtil.toJsonArray(c, false);
	}

	/**
	 *
	 * @param c
	 * @param asArray
	 * @return
	 */
	public static final JsonArray toJsonArray(Collection<?> c, boolean asArray) {
		return JsonUtil.toBuilder(c, asArray ? (jab, o) -> jab.add(JsonUtil.toJsonArray(o)) : (jab, o) -> jab.add(JsonUtil.toJsonObjectBuilder(o))).build();
	}

	private static final JsonArrayBuilder toBuilder(Collection<?> c, BiConsumer<JsonArrayBuilder, Object> builder) {
		final JsonArrayBuilder jab = Json.createArrayBuilder();
		c.stream().sequential().forEach(o -> builder.accept(jab, o));
		return jab;
	}

	/**
	 * TODOC
	 *
	 * @param j
	 * @param jo
	 */
	public static final void setValues(Object j, JsonObject jo) {
		final AnnotatedField<JsonField>[] fields = JsonUtil.jsonFieldsCache.getFields(j.getClass());
		for (int i = 0; i < fields.length; i++) {
			final String name = fields[i].field.getName();
			if (fields[i].annotation.inputable()) {
				TempleUtil.set(j, fields[i].field, JsonUtil.getHandler(fields[i]).getValue(jo, name));
			}
		}
	}

	/**
	 * TODOC
	 *
	 * @param af
	 * @return
	 */
	protected static final Handler getHandler(AnnotatedField<JsonField> af) {
		Handler handler = null;
		Class<? extends Handler> c = af.annotation.handler();
		if (c.isInterface()) {
			final Class<?> fieldType = af.field.getType();
			if (Integer.TYPE.equals(fieldType) || Integer.class.equals(fieldType)) {
				c = IntegerHandler.class;
			} else if (String.class.equals(fieldType)) {
				c = StringHandler.class;
			} else if (Boolean.TYPE.equals(fieldType) || Boolean.class.equals(fieldType)) {
				c = BooleanHandler.class;
			} else if (Double.TYPE.equals(fieldType) || Double.class.equals(fieldType)) {
				c = DoubleHandler.class;
			} else if (fieldType.isEnum()) {
				handler = EnumHandler.getInstance(fieldType.asSubclass(Enum.class));
			} else if (Map.class.isAssignableFrom(fieldType)) {
				c = MapHandler.class;
			} else {
				c = GenericStringHandler.class;
			}
		}
		if (handler == null) {
			handler = JsonUtil.handlerCache.get(c);
			if (handler == null) {
				try {
					handler = c.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					throw new RuntimeException(e);
				}
				JsonUtil.handlerCache.put(c, handler);
			}
		}
		return handler;
	}
}
