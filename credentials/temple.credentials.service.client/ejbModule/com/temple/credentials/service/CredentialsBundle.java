package com.temple.credentials.service;

import com.temple.view.LocaleBundle;

public enum CredentialsBundle implements LocaleBundle {
	DEFAULT;

	private final String baseName = LocaleBundle.BASE_NAME_PREFIX + '.' + this.name().toLowerCase();

	@Override
	public String getBaseName() {
		return this.baseName;
	}
}
