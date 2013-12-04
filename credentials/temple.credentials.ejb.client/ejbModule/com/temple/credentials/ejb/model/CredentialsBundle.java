package com.temple.credentials.ejb.model;

import com.temple.view.LocaleBundle;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public enum CredentialsBundle implements LocaleBundle {
	DEFAULT;

	private final String baseName = LocaleBundle.BASE_NAME_PREFIX + '.' + this.name().toLowerCase();

	@Override
	public String getBaseName() {
		return this.baseName;
	}
}
