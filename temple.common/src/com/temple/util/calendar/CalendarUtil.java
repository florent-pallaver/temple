package com.temple.util.calendar;

import java.util.Calendar;
import java.util.Date;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class CalendarUtil {

	/**
	 * Finds the nearest {@link Date} to today so that one's age will be at least the given age.
	 * 
	 * @param age - a minimum age
	 * @return TODOC
	 */
	public static final Date findMaxDateForMinAge(int age) {
		final Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, -age);
		return c.getTime();
	}

	/**
	 * Finds the farthest {@link Date} from today so that one's age will be at most the given age.
	 * TODOC
	 * 
	 * @param age
	 * @return
	 */
	public static final Date findMinDateForMaxAge(int age) {
		final Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, -age - 1);
		c.add(Calendar.DATE, 1);
		return c.getTime();
	}

	/**
	 * TODOC
	 * 
	 * @param c - a date
	 * @return the age in year corresponding to the given date right now.
	 * @deprecated use {@link #findAge(Date)} instead
	 */
	@Deprecated
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

	/**
	 * TODOC
	 * 
	 * @param date
	 * @return
	 */
	public static final int findAge(Date date) {
		final Calendar c = Calendar.getInstance();
		c.setTime(date);
		return CalendarUtil.findAges(c)[0];
	}

	/**
	 * TODOC
	 * 
	 * @param dates
	 * @return
	 */
	public static final int[] findAges(Calendar... dates) {
		final Calendar now = Calendar.getInstance();
		final int nowY = now.get(Calendar.YEAR);
		final int[] ages = new int[dates.length];
		for (int i = dates.length; i-- > 0;) {
			final int dateY = dates[i].get(Calendar.YEAR);
			now.set(Calendar.YEAR, dateY);
			ages[i] = nowY - dateY + (now.compareTo(dates[i]) < 0 ? -1 : 0);
		}
		return ages;
	}

	private CalendarUtil() {}
}
