package com.temple.service.model;

import java.io.Serializable;

import com.temple.model.TempleEntity;
import com.temple.service.ServiceException;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public class EntityException extends ServiceException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * 
	 * @param model
	 * @param cause
	 */
	public EntityException(TempleEntity po, Throwable cause) {
		this(new Object[]{ po == null ? "null Entity object" : po.toString() }, cause);
	}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param c
	 * @param id
	 * @param cause
	 */
	public EntityException(Class<? extends TempleEntity> c, Serializable id, Throwable cause) {
		this(new Object[]{ c == null ? null : c.getName(), id }, cause);
	}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param parameters
	 * @param cause
	 */
	public EntityException(Object[] parameters, Throwable cause) {
		super(parameters, cause);
	}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param cause
	 */
	public EntityException(Throwable cause) {
		super(cause);
	}
}
