package com.temple.util.json;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import com.temple.util.json.JsonField.Handler;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public final class GenericJsonHandler implements Handler {

	@Override
	public void add(JsonObjectBuilder job, String name, Object value) {
		if (value == null) {
			job.addNull(name);
		} else {
			job.add(name, JsonUtil.toJsonObject(value));
		}
	}

	@Override
	public void add(JsonArrayBuilder jab, Object value) {
		if (value == null) {
			jab.addNull();
		} else {
			jab.add(JsonUtil.toJsonObject(value));
		}
	}

	@Override
	public Object getValue(JsonObject jo, String name) {
		throw new UnsupportedOperationException("This handler is meant to be used for output only !") ;
	}
}
