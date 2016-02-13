package com.temple.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public class DurationConverter implements Converter {

	private static final String[] cache = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09" };

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		final String[] split = arg2.split(":");
		int s = 0;
		for (int i = 0, l = split.length; i < l; i++) {
			s = s * 60 + Integer.valueOf(split[i]);
		}
		return Short.valueOf((short) s);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		final int i = ((Number) arg2).intValue();
		final int s = i % 60;
		final int m = (i - s) % 3600 / 60;
		final int h = i / 3600;
		return DurationConverter.a(h) + ":" + DurationConverter.a(m) + ":" + DurationConverter.a(s);
	}

	private static final String a(int i) {
		return i < 10 ? DurationConverter.cache[i] : Integer.toString(i);
	}
	// public static void main(String[] args) {
	// final DurationConverter dc = new DurationConverter();
	// System.out.println(dc.getAsString(null, null, 3959));
	// System.out.println(dc.getAsObject(null, null, "5:59:17"));
	// System.out.println(dc.getAsString(null, null, 21557));
	// }
}
