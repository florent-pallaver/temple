package com.temple.service.model;

import com.temple.model.TempleEntity;
import java.io.Serializable;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public class UpdateException extends EntityException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param po
	 * @param cause
	 */
	public UpdateException(TempleEntity po, Throwable cause) {
		super(po, cause);
	}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param cause
	 * @param parameters
	 */
	public UpdateException(Throwable cause, Serializable... parameters) {
		super(parameters, cause);
	}
}
