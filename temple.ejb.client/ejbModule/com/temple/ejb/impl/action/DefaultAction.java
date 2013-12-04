package com.temple.ejb.impl.action;

import java.io.Serializable;
import java.util.Map;

import com.temple.Module;
import com.temple.ejb.action.Action;
import com.temple.ejb.action.ActionType;
import com.temple.ejb.action.Property;
import com.temple.impl.view.DefaultLocaleViewable;
import com.temple.model.impl.access.DefaultAccessType;
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
		super(at.key(), values, Module.EJB);
		this.id = id;
		this.actionType = at;
		final String[] keys = new String[values.length];
		for (int i = values.length; i-- > 0; keys[i] = values[i].getLocaleKey()) {}
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
