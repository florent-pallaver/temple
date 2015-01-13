package com.temple.ejb.impl.action;

import com.temple.Module;
import com.temple.ejb.action.ActionType;
import com.temple.ejb.action.PropertyDescription;
import com.temple.model.User;
import com.temple.model.impl.access.DefaultAccessType;
import com.temple.util.human.Language;
import com.temple.view.LocaleViewable;
import com.temple.view.LocaleViewableHelper;

public enum SessionActionType implements ActionType<DefaultAccessType> {
	GET_AVAILABLE_LANGUAGES, // nothing required here
	SET_LANGUAGE(new PropertyDescription("language", Language.class)),
	SIGN_IN(new PropertyDescription("user.name", String.class), new PropertyDescription("user.password", String.class)),
	SIGN_OUT,
	SIGN_UP(new PropertyDescription("user", User.class));

	// IS ALLOWED ??
	private final String localeKey = LocaleViewableHelper.createLocaleKey(this);

	private final PropertyDescription[] descriptions;

	private SessionActionType(PropertyDescription... descriptions) {
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
		return null;
	}

	@Override
	public String getLocaleKey() {
		return this.localeKey;
	}

	@Override
	public Object[] getLocaleParameters() {
		return LocaleViewable.NO_PARAMETERS;
	}

	@Override
	public Module getBundle() {
		return Module.SERVICE;
	}
}
