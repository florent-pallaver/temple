package com.temple.model.impl.access;

import com.temple.Module;
import com.temple.model.access.Role;
import com.temple.view.LocaleViewable;
import com.temple.view.LocaleViewableHelper;

/**
 * A role provide static overriding permissions to perform some {@link Action}.
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public enum DefaultRole implements Role {
	/**
	 * The role to use when there are no roles for a User.
	 */
	GUEST,
	/**
	 * The user role.
	 */
	USER,
	/**
	 * The super user role.
	 */
	SUPERUSER,
	/**
	 * The moderator role.
	 */
	MODERATOR,
	/**
	 * The administrator role.
	 */
	ADMINISTRATOR,
	/**
	 * The system role.
	 */
	SYSTEM,
	/**
	 * The root role.
	 */
	ROOT;

	private final String key = LocaleViewableHelper.createLocaleKey(this);

	@Override
	public String getLocaleKey() {
		return this.key;
	}

	@Override
	public Object[] getLocaleParameters() {
		return LocaleViewable.NO_PARAMETER;
	}

	@Override
	public Module getBundle() {
		return Module.MODEL;
	}

	@Override
	public DefaultRole toEnum() {
		return this;
	}
}
