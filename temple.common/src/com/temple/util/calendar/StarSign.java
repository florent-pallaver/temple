package com.temple.util.calendar;

import java.util.Calendar;
import java.util.Date;

import com.temple.Module;
import com.temple.view.LocaleViewable;
import com.temple.view.LocaleViewableHelper;

/**
 * Enumeration of the 12 Western Star Signs.
 * 
 * @author Florent Pallaver
 * @version 1.0
 * @see #find(Calendar)
 */
public enum StarSign implements LocaleViewable {
	ARIES(19),
	TAURUS(20),
	GEMINI(21),
	CANCER(22),
	LEO(23),
	VIRGO(22),
	LIBRA(23),
	SCORPIO(22),
	SAGITTARIUS(21),
	CAPRICORNUS(19),
	AQUARIUS(18),
	PISCES(20);

	private final String localeKey;

	private final int lastDay;

	private StarSign(int lastDay) {
		this.localeKey = LocaleViewableHelper.createLocaleKey(this);
		this.lastDay = lastDay;
	}

	/**
	 * Finds the {@link StarSign} corresponding to the given date.
	 * 
	 * @param c - a date
	 * @return the star sign corresponding to the given date.
	 */
	public static StarSign find(Calendar c) {
		final int index = c.get(Calendar.MONTH) + 9;
		final StarSign[] values = StarSign.values();
		final StarSign majorSign = values[index % 12];
		final StarSign minorSign = values[(index + 1) % 12];
		return c.get(Calendar.DAY_OF_MONTH) <= majorSign.lastDay ? majorSign : minorSign;
	}

	/**
	 * TODOC
	 * 
	 * @param date
	 * @return
	 */
	public static StarSign find(Date date) {
		final Calendar c = Calendar.getInstance();
		c.setTime(date);
		return StarSign.find(c);
	}

	@Override
	public String getLocaleKey() {
		return this.localeKey;
	}

	@Override
	public Object[] getLocaleParameters() {
		return LocaleViewable.NO_PARAMETERS;
	}

	@Override
	public Module getBundle() {
		return Module.MODEL;
	}
}
