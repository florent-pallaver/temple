package com.temple.util.constraint;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class ConstraintUtil {

	/**
	 * Constructor.
	 */
	protected ConstraintUtil() {}

	/**
	 * TODOC
	 * 
	 * @param s
	 * @param max
	 * @throws LengthException
	 */
	public static final void checkLength(String s, int max) throws LengthException {
		ConstraintUtil.checkLength(s, 0, max);
	}

	/**
	 * TODOC
	 * 
	 * @param s
	 * @param min
	 * @param max
	 * @throws LengthException
	 */
	public static final void checkLength(String s, int min, int max) throws LengthException {
		if (s != null) {
			final int l = s.length();
			if (l < min || l > max) {
				throw new LengthException(s, min, max);
			}
		}
	}
}
