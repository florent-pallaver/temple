package com.temple.view;

import com.temple.util.human.Language;

/**
 * Base implementation of {@link LocaleView}.
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class AbstractLocaleView implements LocaleView {

	private static final long serialVersionUID = 1L;

	private final Language usedLanguage;

	protected AbstractLocaleView(Language usedLanguage) {
		this.usedLanguage = usedLanguage;
	}

	@Override
	public final Language getUsedLanguage() {
		return this.usedLanguage;
	}
}
