package com.temple.service.ejb.action.impl;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

import com.temple.Module;
import com.temple.impl.view.DefaultLocaleViewable;
import com.temple.model.impl.access.DefaultAccessType;
import com.temple.service.ejb.action.Action;
import com.temple.service.ejb.action.ActionType;
import com.temple.service.ejb.action.Property;
import com.temple.util.ImmutableMap;

/**
 * Base implementation of {@link Action}.
 *
 * @author cr9z1k
 * @version 1.0
 */
public class DefaultAction<AT extends ActionType<DefaultAccessType>> extends DefaultLocaleViewable implements Action<AT> {

	private static final long serialVersionUID = 1L;

	private final Serializable id;

	private final AT actionType;

	private final Map<String, Property> properties;

	/**
	 * Constructor
	 *
	 * @param at TODOC
	 * @param key TODOC
	 * @param values TODOC
	 */
	public DefaultAction(Serializable id, AT at, Property[] values) {
		super(at.key(), values, Module.SERVICE);
		this.id = id;
		this.actionType = at;
		final String[] keys = Arrays.stream(values).map(p -> p.getLocaleKey()).toArray(String[]::new);
		this.properties = new ImmutableMap<>(keys, values);
	}

	@Override
	public Serializable getId() {
		return this.id;
	}

	@Override
	public AT getType() {
		return this.actionType;
	}

	@Override
	public Map<String, Property> getProperties() {
		return this.properties;
	}
}
