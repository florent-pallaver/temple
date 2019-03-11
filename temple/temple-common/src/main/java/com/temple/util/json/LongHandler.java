package com.temple.util.json;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public final class LongHandler extends AbstractHandler {

	@Override
	protected void nullSafeAdd(JsonObjectBuilder job, String name, Object value) {
		job.add(name, (Long) value);
	}

	@Override
	protected void nullSafeAdd(JsonArrayBuilder jab, Object value) {
		jab.add((Long) value);
	}

	@Override
	protected Long getNullSafeValue(JsonObject jo, String name) {
		final Long l;
		switch (jo.get(name).getValueType()) {
		case NUMBER:
			l = jo.getJsonNumber(name).longValueExact();
			break;
		case STRING:
			l = Long.valueOf(jo.getString(name));
			break;
		default:
			l = null;
		}
		return l;
	}

}
