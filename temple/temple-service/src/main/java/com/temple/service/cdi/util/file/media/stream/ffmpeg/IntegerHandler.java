package com.temple.service.cdi.util.file.media.stream.ffmpeg;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import com.temple.util.json.JsonField.Handler;

public final class IntegerHandler implements Handler {

	@Override
	public void add(JsonObjectBuilder job, String name, Object value) {
		job.add(name, ((Integer) value).toString());
	}

	@Override
	public void add(JsonArrayBuilder jab, Object value) {
		throw new UnsupportedOperationException() ;
	}

	@Override
	public Integer getValue(JsonObject jo, String name) {
		final Integer i;
		if(jo.containsKey(name)) {
			switch(jo.get(name).getValueType()) {
			case NUMBER:
				i = jo.getJsonNumber(name).intValueExact();
				break;
			case STRING:
				i = Integer.valueOf(jo.getString(name));
				break;
			default:
				i = null;
			}
		} else {
			i = null;
		}
		return i;
	}
}
