package com.temple.impl.view;

import com.temple.Module;
import com.temple.util.human.Language;
import com.temple.view.AbstractLocaleView;
import com.temple.view.LocaleBundle;
import com.temple.view.LocaleStringView;
import com.temple.view.LocaleStringViewHelper;
import com.temple.view.LocaleViewable;

/**
 * Default implementation of {@link LocaleStringView}.
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public class DefaultLocaleStringView extends AbstractLocaleView implements LocaleStringView {

	private static final long serialVersionUID = 1L;

	private final String simple;

	private final String detailed;

	/**
	 * Constructor.
	 * 
	 * @param language - a language to create the localized Strings of this view.
	 */
	protected DefaultLocaleStringView(Language language) {
		this(language, Module.DEFAULT);
	}

	/**
	 * Constructor.
	 * 
	 * @param language - a language to create the localized Strings of this view.
	 * @param bundle - TODOC
	 */
	protected DefaultLocaleStringView(Language language, LocaleBundle bundle) {
		this(language, LocaleViewable.NO_PARAMETERS, bundle);
	}

	/**
	 * Constructor.
	 * 
	 * @param language - a language to create the localized Strings of this view.
	 * @param parameters - TODOC
	 * @param bundle - TODOC
	 */
	protected DefaultLocaleStringView(Language language, Object[] parameters, LocaleBundle bundle) {
		this(language, null, parameters, bundle);
	}

	/**
	 * Constructor.
	 * 
	 * @param language - a language to create the localized Strings of this view.
	 * @param viewable TODOC
	 */
	public DefaultLocaleStringView(Language language, LocaleViewable viewable) {
		this(language, viewable.getLocaleKey(), viewable.getLocaleParameters(), viewable.getBundle());
	}

	/**
	 * String
	 * Constructor.
	 * 
	 * @param language - a language to create the localized Strings of this view.
	 * @param key - TODOC
	 * @param bundle - TODOC
	 */
	public DefaultLocaleStringView(Language language, String key, LocaleBundle bundle) {
		this(language, key, LocaleViewable.NO_PARAMETERS, bundle);
	}

	/**
	 * String
	 * Constructor.
	 * 
	 * @param language - a language to create the localized Strings of this view.
	 * @param key - TODOC
	 * @param parameters - TODOC
	 * @param bundle - TODOC
	 */
	public DefaultLocaleStringView(Language language, String key, Object[] parameters, LocaleBundle bundle) {
		super(language);
		final DefaultLocaleViewable a = new DefaultLocaleViewable(key == null || key.length() < 1 ? this.getClass().getName() : key, parameters, bundle);
		this.simple = LocaleStringViewHelper.createLocaleString(a, language, false);
		this.detailed = LocaleStringViewHelper.createLocaleString(a, language, true);
	}

	@Override
	public final String getString() {
		return this.simple;
	}

	@Override
	public final String getDetailedString() {
		return this.detailed;
	}
}
