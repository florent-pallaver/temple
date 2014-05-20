package com.temple.util.json;

import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import com.temple.util.json.JsonField.Handler;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public final class BooleanHandler implements Handler {

	@Override
	public void add(JsonObjectBuilder job, String name, Object value) {
		job.add(name, (Boolean) value);
	}

	@Override
	public Object getValue(JsonObject jo, String name) {
		return jo.getBoolean(name);
	}
}
