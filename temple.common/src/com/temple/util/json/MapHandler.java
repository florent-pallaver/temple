package com.temple.util.json;

import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonString;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public final class MapHandler extends AbstractHandler {

	@Override
	protected void nullSafeAdd(JsonObjectBuilder job, String name, Object value) {
		job.add(name, this.toJsonObjectBuilder(value));
	}

	@Override
	protected void nullSafeAdd(JsonArrayBuilder jab, Object value) {
		jab.add(this.toJsonObjectBuilder(value));
	}

	private JsonObjectBuilder toJsonObjectBuilder(Object value) {
		final JsonObjectBuilder mjob = Json.createObjectBuilder();
		((Map<?, ?>) value).forEach((k, v) -> {
			if (v == null) {
				mjob.addNull(k.toString());
			} else {
				mjob.add(k.toString(), v.toString());
			}
		});
		return mjob;
	}

	@Override
	protected Object getNullSafeValue(JsonObject jo, String name) {
		final Map<String, String> m = new HashMap<>();
		jo.getJsonObject(name).forEach((s, jv) -> m.put(s, ((JsonString) jv).getString()));
		return m;
	}

}
