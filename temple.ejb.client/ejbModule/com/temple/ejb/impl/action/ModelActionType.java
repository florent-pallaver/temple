package com.temple.ejb.impl.action;

import com.temple.Module;
import com.temple.ejb.action.ActionType;
import com.temple.ejb.action.PropertyDescription;
import com.temple.model.Resource;
import com.temple.model.impl.access.DefaultAccessType;
import com.temple.view.LocaleViewable;
import com.temple.view.LocaleViewableHelper;

public enum ModelActionType implements ActionType<DefaultAccessType> {
	GET(DefaultAccessType.READ, new PropertyDescription("type", Class.class), new PropertyDescription("id", Integer.class)),
	// GET_ALL ... with Filter as a property
	CREATE(DefaultAccessType.WRITE, new PropertyDescription("type", Class.class), new PropertyDescription("data", Resource.class)),
	UPDATE(DefaultAccessType.WRITE, new PropertyDescription("type", Class.class), new PropertyDescription("data", Resource.class)),
	DELETE(DefaultAccessType.WRITE, new PropertyDescription("type", Class.class), new PropertyDescription("id", Integer.class));

	private final String localeKey = LocaleViewableHelper.createLocaleKey(this);

	private final DefaultAccessType family;

	private final PropertyDescription[] descriptions;

	private ModelActionType(DefaultAccessType f, PropertyDescription... descriptions) {
		this.family = f;
		this.descriptions = descriptions;
	}

	@Override
	public String key() {
		return this.localeKey;
	}

	@Override
	public PropertyDescription[] propertiesDescription() {
		return this.descriptions;
	}

	@Override
	public DefaultAccessType getAccessType() {
		return this.family;
	}

	@Override
	public String getLocaleKey() {
		return this.localeKey;
	}

	@Override
	public Object[] getLocaleParameters() {
		return LocaleViewable.NO_PARAMETER;
	}

	@Override
	public Module getBundle() {
		return Module.EJB;
	}
}
