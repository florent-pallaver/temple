package com.temple.util.json;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public final class IntegerHandler extends AbstractHandler {

	@Override
	protected void nullSafeAdd(JsonObjectBuilder job, String name, Object value) {
		job.add(name, (Integer) value);
	}

	@Override
	protected void nullSafeAdd(JsonArrayBuilder jab, Object value) {
		jab.add((Integer) value);
	}

	@Override
	protected Integer getNullSafeValue(JsonObject jo, String name) {
		return jo.getInt(name);
	}

}
