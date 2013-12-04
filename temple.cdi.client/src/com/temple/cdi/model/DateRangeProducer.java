package com.temple.cdi.model;

import java.util.Calendar;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
@ApplicationScoped
public class DateRangeProducer {

	private static final int[] _days = new int[31];

	private static final int[] _months = new int[12];

	DateRangeProducer() {}

	// TODO creer ageconstraint et daterange profilebean a le field: birthday et daterange est injecte named
	@Produces
	@Dependent
	@AgeLimit(max = 0)
	DateInRange create(InjectionPoint ip) {
		return new DateInRange(DateRangeProducer._days.clone(), DateRangeProducer._months.clone(), this.getYears(ip));
	}

	private int[] getYears(InjectionPoint ip) {
		final AgeLimit ageLimit = ip.getAnnotated().getAnnotation(AgeLimit.class);
		// TOTHINK cache and clone the array
		final short max = ageLimit.max();
		final int[] years = new int[max - ageLimit.min() + 1];
		// TOTHINK should work fine with server reboots...
		for (int i = years.length, y = Calendar.getInstance().get(Calendar.YEAR); i-- > 0;) {
			years[i] = y + i - max;
		}
		return years;
	}

	static {
		for (int i = DateRangeProducer._days.length; i-- > 0;) {
			DateRangeProducer._days[i] = i + 1;
		}
		for (int i = DateRangeProducer._months.length; i-- > 0;) {
			DateRangeProducer._months[i] = i + 1;
		}
	}
}
