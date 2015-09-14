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
public final class StringHandler implements Handler {

	@Override
	public void add(JsonObjectBuilder job, String name, Object value) {
		if (value == null) {
			job.addNull(name);
		} else {
			job.add(name, (String) value);
		}
	}

	@Override
	public Object getValue(JsonObject jo, String name) {
		return !jo.containsKey(name) || jo.isNull(name) ? null : jo.getString(name);
	}
}
