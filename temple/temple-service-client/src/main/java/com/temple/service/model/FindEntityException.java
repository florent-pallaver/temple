package com.temple.service.model;

import java.io.Serializable;

import com.temple.model.TempleEntity;
import com.temple.model.filter.EntityFilter;

public class FindEntityException extends EntityException {

	private static final long serialVersionUID = 1L;

	public FindEntityException(Class<? extends TempleEntity> clazz, Serializable key) {
		super(clazz, key, null);
	}

	public FindEntityException(Class<? extends TempleEntity> clazz, Serializable key, Throwable cause) {
		super(clazz, key, cause);
	}

	public FindEntityException(Class<? extends TempleEntity> clazz, Throwable cause) {
		super(new Serializable[] { clazz.getName() }, cause);
	}

	public FindEntityException(EntityFilter<? extends TempleEntity, ? extends Serializable> filter, Throwable cause) {
		super(new Serializable[] { filter }, cause);
	}
}
