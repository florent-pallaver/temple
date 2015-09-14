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
public class SizeConverter implements Converter {

	private static final String[] units = { "", "k", "M", "G", "T", "P" };

	private static final double[] factors = { Math.pow(1000, 0), Math.pow(1000, 1), Math.pow(1000, 2), Math.pow(1000, 3), Math.pow(1000, 4), Math.pow(1000, 5) };

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		// TODOC NOT SUPPORTED
		return 0;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		final double s = ((Number) arg2).doubleValue();
		for (int i = 0, l = SizeConverter.factors.length - 1; i < l; i++) {
			if (s < SizeConverter.factors[i + 1]) {
				return Math.round(s / SizeConverter.factors[i]) + " " + SizeConverter.units[i] + 'B';
			}
		}
		return "Unknown";
	}
	// public static void main(String[] args) {
	// final SizeConverter dc = new SizeConverter();
	// System.out.println(dc.getAsString(null, null, 359));
	// System.out.println(dc.getAsString(null, null, 21557));
	// System.out.println(dc.getAsString(null, null, 421557));
	// System.out.println(dc.getAsString(null, null, 5421557));
	// System.out.println(dc.getAsString(null, null, 98421557));
	// System.out.println(dc.getAsString(null, null, 756421557));
	// System.out.println(dc.getAsString(null, null, 8756421557L));
	// System.out.println(dc.getAsString(null, null, 18756421557L));
	// System.out.println(dc.getAsString(null, null, 268756421557L));
	// System.out.println(dc.getAsString(null, null, 6758756421557L));
	// }
}
