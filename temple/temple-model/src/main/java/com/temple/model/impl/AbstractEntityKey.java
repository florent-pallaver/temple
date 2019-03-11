package com.temple.model.impl;

import java.io.Serializable;

import com.temple.model.EntityKey;
import com.temple.model.TempleEntity;

public abstract class AbstractEntityKey<E extends TempleEntity, ID extends Serializable> implements EntityKey<E, ID> {

	private final Class<E> entityClass;

	protected AbstractEntityKey(Class<E> entityClass) {
		super();
		this.entityClass = entityClass;
	}

	@Override
	public Class<E> getEntityClass() {
		return this.entityClass;
	}
}
