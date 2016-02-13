package com.temple.impl.util.human;

import java.util.Locale;

import com.temple.util.human.Language;

/**
 * TODOC <br>
 * See ISO 639-1 codes
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public enum LanguageEnum implements Language {
	ENGLISH(Locale.ENGLISH),
	SPANISH(new Locale("es")),
	FRENCH(Locale.FRENCH),
	CHINESE(Locale.CHINESE), // check
	PORTUGESE(new Locale("pt")),
	GERMAN(Locale.GERMAN),
	ITALIAN(Locale.ITALIAN),
	JAPANESE(Locale.JAPANESE),
	RUSSIAN(new Locale("ru")),
	ROMANIAN(new Locale("ro")),
	OTHER(Locale.ROOT, "other");

	private final Locale locale;

	private final String name;

	private LanguageEnum(Locale locale) {
		this.locale = locale;
		this.name = locale.getDisplayLanguage(locale);
	}

	private LanguageEnum(Locale locale, String name) {
		this.locale = locale;
		this.name = name;
	}

	@Override
	public Locale getLocale() {
		return this.locale;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Language getUsedLanguage() {
		return this;
	}

	@Override
	public String getString() {
		return this.name;
	}

	@Override
	public String getDetailedString() {
		return this.name;
	}

	@Override
	public LanguageEnum toEnum() {
		return this;
	}
}
