package com.temple.model.impl;

import com.temple.model.EntityKey;
import com.temple.model.TempleEntity;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @param <E>
 */
public abstract class AbstractEntityKey<E extends TempleEntity> implements EntityKey<E> {

	private static final long serialVersionUID = 1L;

	private final Class<E> entityClass;

	/**
	 * Constructor.
	 * TODOC
	 *
	 * @param entityClass
	 */
	protected AbstractEntityKey(Class<E> entityClass) {
		super();
		this.entityClass = entityClass;
	}

	@Override
	public Class<E> getEntityClass() {
		return this.entityClass;
	}
}
