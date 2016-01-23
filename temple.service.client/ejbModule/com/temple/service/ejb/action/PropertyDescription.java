package com.temple.service.ejb.action;

import java.io.Serializable;

import com.temple.TempleObject;

/**
 * TODOC
 * 
 * @author cr9z1k
 * @version 1.0
 */
public final class PropertyDescription extends TempleObject {

	private final String key;

	private final Class<? extends Serializable> type;

	private transient Object[] naturalKey;

	public PropertyDescription(String key, Class<? extends Serializable> type) {
		super();
		this.key = key;
		this.type = type;
		this.naturalKey = null;
	}

	/**
	 * @return
	 */
	public String key() {
		return this.key;
	}

	/**
	 * @return
	 */
	public Class<? extends Serializable> type() {
		return this.type;
	}

	@Override
	protected Object[] naturalKey() {
		if (this.naturalKey == null) {
			this.naturalKey = new Object[] { this.key, this.type };
		}
		return this.naturalKey;
	}
}
