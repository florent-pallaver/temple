package com.temple.cdi.model;

import java.io.Serializable;
import java.util.Calendar;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public class DateRange implements Serializable {

	private static final long serialVersionUID = 1L;

	private transient Calendar calendar;

	private final int[] allDays;

	private final int[] allMonths;

	private final int[] allYears;

	private Integer day;

	private Integer month;

	private Integer year;

	DateRange(int[] allDays, int[] allMonths, int[] allYears) {
		super();
		this.allDays = allDays;
		this.allMonths = allMonths;
		this.allYears = allYears;
	}

	/**
	 * @return the allDays
	 */
	public int[] getAllDays() {
		return this.allDays;
	}

	/**
	 * @return the allMonths
	 */
	public int[] getAllMonths() {
		return this.allMonths;
	}

	/**
	 * @return the allYears
	 */
	public int[] getAllYears() {
		return this.allYears;
	}

	/**
	 * @return the day
	 */
	public Integer getDay() {
		return this.day;
	}

	/**
	 * Sets the day
	 * 
	 * @param day the day to set
	 */
	public void setDay(Integer day) {
		this.day = day;
		this.calendar = null;
	}

	/**
	 * @return the month
	 */
	public Integer getMonth() {
		return this.month;
	}

	/**
	 * Sets the month
	 * 
	 * @param month the month to set
	 */
	public void setMonth(Integer month) {
		this.month = month;
		this.calendar = null;
	}

	/**
	 * @return the year
	 */
	public Integer getYear() {
		return this.year;
	}

	/**
	 * Sets the year
	 * 
	 * @param year the year to set
	 */
	public void setYear(Integer year) {
		this.year = year;
		this.calendar = null;
	}

	/**
	 * TODOC
	 * 
	 * @param c
	 */
	public void setDate(Calendar c) {
		if (c != null) {
			this.day = Integer.valueOf(c.get(Calendar.DAY_OF_MONTH));
			this.month = Integer.valueOf(c.get(Calendar.MONTH) + 1);
			this.year = Integer.valueOf(c.get(Calendar.YEAR));
		} else {
			this.day = null;
			this.month = null;
			this.year = null;
		}
		this.calendar = null;
	}

	/**
	 * TODOC
	 * 
	 * @return
	 */
	public Calendar getCalendar() {
		final Calendar c;
		if (this.day != null && this.month != null && this.year != null) {
			if (this.calendar != null) {
				c = this.calendar;
			} else {
				c = Calendar.getInstance();
				c.set(this.year.intValue(), this.month.intValue() - 1, this.day.intValue(), 0, 0, 0);
				this.calendar = c;
			}
		} else {
			c = null;
		}
		return c;
	}
}
