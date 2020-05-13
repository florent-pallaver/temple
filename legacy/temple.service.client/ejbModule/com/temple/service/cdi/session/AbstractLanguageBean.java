package com.temple.service.cdi.session;

import java.io.Serializable;

import com.temple.Module;
import com.temple.impl.view.DefaultLocaleStringView;
import com.temple.impl.view.DefaultLocaleViewable;
import com.temple.service.cdi.AbstractCDIBean;
import com.temple.util.human.Language;
import com.temple.view.LocaleBundle;
import com.temple.view.LocaleStringView;
import com.temple.view.LocaleStringViewHelper;
import com.temple.view.LocaleViewable;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class AbstractLanguageBean extends AbstractCDIBean implements LanguageBean {

	private static final long serialVersionUID = 1L;

	private Language currentLanguage;

	private LocaleBundle defaultBundle;

	AbstractLanguageBean() {
		super();
	}

	/**
	 * Constructor
	 *
	 * @param currentLanguage
	 */
	protected AbstractLanguageBean(Language currentLanguage) {
		this(currentLanguage, Module.DEFAULT);
	}

	/**
	 * Constructor
	 * @param defaultBundle
	 * @param currentLanguage
	 */
	protected AbstractLanguageBean(Language currentLanguage, LocaleBundle defaultBundle) {
		this();
		this.currentLanguage = currentLanguage;
		this.defaultBundle = defaultBundle;
	}

	@Override
	public Language getCurrentLanguage() {
		return this.currentLanguage;
	}

	@Override
	public void setCurrentLanguage(Language language) {
		this.currentLanguage = language;
	}

	@Override
	public LocaleStringView getStringView(LocaleViewable lv) {
		return new DefaultLocaleStringView(this.currentLanguage, lv);
	}

	@Override
	public String getString(LocaleViewable lv) {
		return LocaleStringViewHelper.createLocaleString(lv, this.currentLanguage, false);
	}

	@Override
	public String getString(String key, Serializable... parameters) {
		return this.getString(new DefaultLocaleViewable(key, parameters, this.defaultBundle));
	}

	@Override
	public String getDetailedString(LocaleViewable lv) {
		return LocaleStringViewHelper.createLocaleString(lv, this.currentLanguage, true);
	}

	@Override
	public String getDetailedString(String key, Serializable... parameters) {
		return this.getDetailedString(new DefaultLocaleViewable(key, parameters, this.defaultBundle));
	}
}
