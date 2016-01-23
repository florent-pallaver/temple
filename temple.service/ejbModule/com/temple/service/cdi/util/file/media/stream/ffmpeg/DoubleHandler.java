package com.temple.service.cdi.util.file.media.stream.ffmpeg;

import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import com.temple.util.json.JsonField.Handler;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public final class DoubleHandler implements Handler {

	@Override
	public void add(JsonObjectBuilder job, String name, Object value) {
		job.add(name, ((Double) value).toString());
	}

	@Override
	public Object getValue(JsonObject jo, String name) {
		return jo.containsKey(name) ? Double.valueOf(jo.getString(name)) : null;
	}
}
