package com.temple.util.calendar;

import java.util.Calendar;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class CalendarUtil {

	/**
	 * TODOC
	 * 
	 * @param c - a date
	 * @return the age corresponding to the given date right now.
	 */
	public static final int getAge(Calendar c) {
		final Calendar now = Calendar.getInstance();
		final int thisYear = now.get(Calendar.YEAR);
		final int thisMonth = now.get(Calendar.MONTH);
		final int thisDay = now.get(Calendar.DAY_OF_MONTH);
		final int birthYear = c.get(Calendar.YEAR);
		final int birthMonth = c.get(Calendar.MONTH);
		final int birthDay = c.get(Calendar.DAY_OF_MONTH);
		return thisYear - birthYear - (thisMonth == birthMonth && thisDay < birthDay || thisMonth < birthMonth ? 1 : 0);
	}

	private CalendarUtil() {}
}
