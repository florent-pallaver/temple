package com.temple.util.json;

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
public abstract class AbstractHandler implements Handler {

	@Override
	public final void add(JsonObjectBuilder job, String name, Object value) {
		if (value == null) {
			job.addNull(name);
		} else {
			this.nullSafeAdd(job, name, value);
		}
	}

	protected abstract void nullSafeAdd(JsonObjectBuilder job, String name, Object value);

	@Override
	public final void add(JsonArrayBuilder jab, Object value) {
		if (value == null) {
			jab.addNull();
		} else {
			this.nullSafeAdd(jab, value);
		}
	}

	protected abstract void nullSafeAdd(JsonArrayBuilder job, Object value);

	@Override
	public final Object getValue(JsonObject jo, String name) {
		return !jo.containsKey(name) || jo.isNull(name) ? null : this.getNullSafeValue(jo, name);
	}

	protected abstract Object getNullSafeValue(JsonObject jo, String name);

}
