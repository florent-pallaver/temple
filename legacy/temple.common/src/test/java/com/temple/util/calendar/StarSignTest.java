package com.temple.util.calendar;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import com.temple.util.calendar.StarSign;

public class StarSignTest {

	@Test
	public void testFind() {
		final Calendar _04jan2012 = Calendar.getInstance();
		_04jan2012.set(2012, 0, 4);
		Assert.assertEquals(StarSign.CAPRICORNUS, StarSign.find(_04jan2012));
		final Calendar _12jun1984 = Calendar.getInstance();
		_12jun1984.set(1984, 5, 12);
		Assert.assertEquals(StarSign.GEMINI, StarSign.find(_12jun1984));
		final Calendar _28sep1853 = Calendar.getInstance();
		_28sep1853.set(1853, 8, 28);
		Assert.assertEquals(StarSign.LIBRA, StarSign.find(_28sep1853));
		final Calendar _19mar2013 = Calendar.getInstance();
		_19mar2013.set(2013, 3, 19);
		Assert.assertEquals(StarSign.ARIES, StarSign.find(_19mar2013));
	}
}
