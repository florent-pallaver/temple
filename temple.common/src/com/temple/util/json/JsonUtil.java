package com.temple.util.json;

import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
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
	protected JsonUtil() {}

	/**
	 * TODOC
	 * 
	 * @param j
	 * @return
	 */
	public static final JsonObject toJsonObject(Object j) {
		final AnnotatedField<JsonField>[] fields = JsonUtil.jsonFieldsCache.getFields(j.getClass());
		final JsonObjectBuilder job = Json.createObjectBuilder();
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].annotation.outputable()) {
				final Object value = TempleUtil.get(j, fields[i].field);
				final String name = fields[i].field.getName();
				JsonUtil.getHandler(fields[i]).add(job, name, value);
			}
		}
		return job.build();
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
		final Class<? extends Handler> c = af.annotation.handler();
		Handler handler = JsonUtil.handlerCache.get(c);
		if (handler == null) {
			try {
				handler = c.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			JsonUtil.handlerCache.put(c, handler);
		}
		return handler;
	}
}
