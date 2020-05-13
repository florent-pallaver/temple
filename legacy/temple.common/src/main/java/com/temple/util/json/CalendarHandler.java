package com.temple.util.json;

import java.util.Calendar;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public final class CalendarHandler extends AbstractHandler {

	@Override
	protected void nullSafeAdd(JsonObjectBuilder job, String name, Object value) {
		job.add(name, Long.toString(((Calendar) value).getTimeInMillis()));
	}

	@Override
	protected void nullSafeAdd(JsonArrayBuilder jab, Object value) {
		jab.add(Long.toString(((Calendar) value).getTimeInMillis()));
	}

	@Override
	protected Object getNullSafeValue(JsonObject jo, String name) {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(Long.valueOf(jo.getString(name))) ;
		return calendar ;
	}

}
