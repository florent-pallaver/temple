package com.temple.util.constraint;

import java.io.Serializable;


/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public class LengthException extends ConstraintException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param string
	 * @param max
	 */
	public LengthException(String string, int max) {
		this(string, 0, max);
	}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param string
	 * @param min
	 * @param max
	 */
	public LengthException(String string, int min, int max) {
		super(new Serializable[]{ string, min, max });
	}
}
