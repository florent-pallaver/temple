package com.temple.util.calendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
	 * Date time format as defined in RFC 3339
	 * <br>
	 * Example: 2015-02-20T15:21:15-08:00
	 * @see http://tools.ietf.org/html/rfc3339
	 */
	public static final DateFormat HTML_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX") ;

	/**
	 * Full Date time format
	 * <br>
	 * Example: 2020-02-12 21:52:07 
	 */
	public static final DateFormat SQL_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;

	/**
	 * Simple date format
	 * <br>
	 * Example: 2015-03-26
	 */
	public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
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
