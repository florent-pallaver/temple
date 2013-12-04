package com.temple.cdi.impl.session;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.temple.cdi.TempleBean;
import com.temple.cdi.application.ApplicationManager;
import com.temple.cdi.impl.AbstractCDIBean;
import com.temple.cdi.session.LanguageBean;
import com.temple.impl.view.DefaultLocaleViewable;
import com.temple.util.human.Language;
import com.temple.view.LocaleStringViewHelper;
import com.temple.view.LocaleViewable;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
@Named("languageBean")
@SessionScoped
@TempleBean
public class TempleLanguageBean extends AbstractCDIBean implements LanguageBean, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	@TempleBean
	private ApplicationManager am;

	// @Inject
	private Language currentLanguage;

	TempleLanguageBean() {
		super();
	}

	@Override
	public Language getCurrentLanguage() {
		return this.currentLanguage;
	}

	@Override
	public void setCurrentLanguage(Language language) {
		this.currentLanguage = language;
		FacesContext.getCurrentInstance().getViewRoot().setLocale(language.getLocale());
	}

	@Override
	public String getString(LocaleViewable lv) {
		return LocaleStringViewHelper.createLocaleString(lv, this.currentLanguage, false);
	}

	@Override
	public String getString(String key, Object... parameters) {
		return this.getString(new DefaultLocaleViewable(key, parameters, this.am.getLocaleBundle()));
	}

	@Override
	public String getDetailedString(LocaleViewable lv) {
		return LocaleStringViewHelper.createLocaleString(lv, this.currentLanguage, true);
	}

	@Override
	public String getDetailedString(String key, Object... parameters) {
		return this.getDetailedString(new DefaultLocaleViewable(key, parameters, this.am.getLocaleBundle()));
	}
}
