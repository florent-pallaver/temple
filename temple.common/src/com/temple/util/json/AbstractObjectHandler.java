package com.temple.util.json;

import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import com.temple.util.json.JsonField.Handler;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class AbstractObjectHandler implements Handler {

	@Override
	public final void add(JsonObjectBuilder job, String name, Object value) {
		job.add(name, JsonUtil.toJsonObject(value));
	}

	@Override
	public final Object getValue(JsonObject jo, String name) {
		return jo.containsKey(name) ? this.newObject(jo.getJsonObject(name)) : null;
	}

	/**
	 * TODOC
	 * 
	 * @param jo
	 * @return
	 */
	protected abstract Object newObject(JsonObject jo);
}
