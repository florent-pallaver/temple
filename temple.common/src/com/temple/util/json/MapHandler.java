package com.temple.util.json;

import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
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
public final class MapHandler implements Handler {

	@Override
	public void add(JsonObjectBuilder job, String name, Object value) {
		if (value == null) {
			job.addNull(name);
		} else {
			job.add(name, this.toJsonObjectBuilder(value)) ;
		}
	}

	@Override
	public void add(JsonArrayBuilder jab, Object value) {
		if (value == null) {
			jab.addNull();
		} else {
			jab.add(this.toJsonObjectBuilder(value)) ;
		}
	}

	private JsonObjectBuilder toJsonObjectBuilder(Object value) {
		final JsonObjectBuilder mjob = Json.createObjectBuilder() ;
		((Map<?, ?>) value).forEach((k,v) -> {
			if(v == null) {
				mjob.addNull(k.toString()) ;
			} else {
				mjob.add(k.toString(), v.toString()) ;
			}
		});
		return mjob ;
	}

	@Override
	public Object getValue(JsonObject jo, String name) {
		Map<String, String> m ;
		if(!jo.containsKey(name) || jo.isNull(name)) {
			m = null ;
		} else {
			m = new HashMap<>() ;
			jo.getJsonObject(name).forEach((s, jv) -> m.put(s, jv.toString()));
		}
		return m ;
	}
}
