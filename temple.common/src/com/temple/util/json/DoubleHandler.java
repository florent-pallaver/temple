package com.temple.util.json;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public class DoubleHandler extends AbstractHandler {

	@Override
	protected void nullSafeAdd(JsonObjectBuilder job, String name, Object value) {
		job.add(name, (Double) value);
	}

	@Override
	protected void nullSafeAdd(JsonArrayBuilder jab, Object value) {
		jab.add((Double) value);
	}

	@Override
	protected Object getNullSafeValue(JsonObject jo, String name) {
		return jo.getJsonNumber(name).doubleValue();
	}

}
