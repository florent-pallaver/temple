package com.temple.util.json;

import com.temple.util.json.JsonField.Handler;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public final class GenericStringHandler implements Handler {

	@Override
	public void add(JsonObjectBuilder job, String name, Object value) {
		if (value == null) {
			job.addNull(name);
		} else {
			job.add(name, value.toString());
		}
	}

	@Override
	public Object getValue(JsonObject jo, String name) {
		throw new UnsupportedOperationException("This handler is meant to be used for output only !") ;
	}
}
