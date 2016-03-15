package com.temple.service.cdi.util.file.media.stream.ffmpeg;

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
	public Object getValue(JsonObject jo, String name) {
		return jo.containsKey(name) ? Integer.valueOf(jo.getString(name)) : null;
	}
}
