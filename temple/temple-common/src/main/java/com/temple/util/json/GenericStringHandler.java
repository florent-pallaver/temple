package com.temple.util.json;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

public final class GenericStringHandler extends AbstractOutputOnlyHandler  {

	@Override
	protected void nullSafeAdd(JsonObjectBuilder job, String name, Object value) {
		job.add(name, value.toString());
	}

	@Override
	protected void nullSafeAdd(JsonArrayBuilder jab, Object value) {
		jab.add(value.toString());

	}

}
