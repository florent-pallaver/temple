package com.temple.util.json;

import com.temple.util.json.JsonField.Handler;
import java.util.HashMap;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

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
			final JsonObjectBuilder mjob = Json.createObjectBuilder() ;
			((Map<?, ?>) value).forEach((k,v) -> {
				if(v == null) {
					mjob.addNull(k.toString()) ;
				} else {
					mjob.add(k.toString(), v.toString()) ; 
				}
			});
			job.add(name, mjob) ;
		}
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
