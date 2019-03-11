package com.temple.service.cdi.model.validation;

import java.util.Calendar;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.temple.service.cdi.model.AgeLimit;
import com.temple.service.cdi.model.DateInRange;
import com.temple.util.calendar.CalendarUtil;

/**
 * TODOC
 * Allows null values as non null checks are to be done by other validators...
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public class AgeLimitValidator implements ConstraintValidator<AgeLimit, DateInRange> {

	private int min;

	private int max;

	@Override
	public void initialize(AgeLimit constraint) {
		this.min = constraint.min();
		this.max = constraint.max();
	}

	@Override
	public boolean isValid(DateInRange value, ConstraintValidatorContext context) {
		final boolean b;
		final Calendar c = value.getCalendar();
		if (c == null) {
			b = true;
		} else {
			final int age = CalendarUtil.findAges(c)[0];
			b = this.min <= age && age <= this.max;
		}
		return b;
	}
}
