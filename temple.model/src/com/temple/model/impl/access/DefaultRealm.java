package com.temple.model.impl.access;

import com.temple.Module;
import com.temple.model.Resource;
import com.temple.model.User;
import com.temple.model.access.Realm;
import com.temple.view.LocaleViewable;
import com.temple.view.LocaleViewableHelper;

/**
 * {@link Realm} implementation.
 */
public enum DefaultRealm implements Realm {
	/**
	 * The realm to use when no other is usable.
	 */
	OTHER,
	/**
	 * The realm to use when an {@link User} has signed in.
	 */
	SIGNED,
	/**
	 * The realm to use when an {@link User} is a member of any {@link Group} of a {@link Resource}.
	 */
	GROUP,
	/**
	 * The realm to use when an {@link User} is the owner of a {@link Resource}.
	 */
	OWNER;

	private final String key = LocaleViewableHelper.createLocaleKey(this);

	@Override
	public final String getLocaleKey() {
		return this.key;
	}

	@Override
	public final Object[] getLocaleParameters() {
		return LocaleViewable.NO_PARAMETER;
	}

	@Override
	public final Module getBundle() {
		return Module.MODEL;
	}

	@Override
	public final DefaultRealm toEnum() {
		return this;
	}
}
