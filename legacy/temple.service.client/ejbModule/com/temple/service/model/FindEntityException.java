package com.temple.service.model;

import java.io.Serializable;

import com.temple.model.EntityKey;
import com.temple.model.TempleEntity;
import com.temple.model.filter.EntityFilter;
import com.temple.model.filter.FindMaxFilter;

/**
 * TODOC
 *
 * @author Florent Pallaver
 */
public class FindEntityException extends EntityException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor. TODOC
	 *
	 * @param clazz
	 * @param key
	 */
	public FindEntityException(Class<? extends TempleEntity> clazz, Serializable key) {
		super(clazz, key, null);
	}

	/**
	 * Constructor. TODOC
	 *
	 * @param clazz
	 * @param key
	 * @param cause
	 */
	public FindEntityException(Class<? extends TempleEntity> clazz, Serializable key, Throwable cause) {
		super(clazz, key, cause);
	}

	/**
	 * Constructor. TODOC
	 *
	 * @param key
	 * @param cause
	 */
	public FindEntityException(EntityKey<?> key, Throwable cause) {
		super(new Serializable[] { key }, cause);
	}

	/**
	 * Constructor. TODOC
	 *
	 * @param clazz
	 * @param cause
	 */
	public FindEntityException(Class<? extends TempleEntity> clazz, Throwable cause) {
		super(new Serializable[] { clazz.getName() }, cause);
	}

	/**
	 * Constructor. TODOC
	 *
	 * @param filter
	 * @param cause
	 */
	public FindEntityException(EntityFilter<? extends TempleEntity, ? extends Serializable> filter, Throwable cause) {
		super(new Serializable[] { filter }, cause);
	}

	/**
	 * Constructor. TODOC
	 *
	 * @param algo
	 * @param cause
	 */
	public FindEntityException(FindMaxFilter<?> algo, Throwable cause) {
		super(new Serializable[] { algo }, cause);
	}
}
