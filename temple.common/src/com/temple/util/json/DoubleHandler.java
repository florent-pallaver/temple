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
public class DoubleHandler implements Handler {

	@Override
	public void add(JsonObjectBuilder job, String name, Object value) {
		job.add(name, (Double) value);
	}

	@Override
	public void add(JsonArrayBuilder jab, Object value) {
		jab.add((Double) value);
	}

	@Override
	public Object getValue(JsonObject jo, String name) {
		return Double.parseDouble(jo.getString(name));
	}
}
