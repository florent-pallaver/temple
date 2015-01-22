package com.temple.service.cdi.session;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.temple.impl.view.DefaultLocaleStringView;
import com.temple.impl.view.DefaultLocaleViewable;
import com.temple.service.cdi.AbstractCDIBean;
import com.temple.service.cdi.ApplicationBean;
import com.temple.service.cdi.CDIConfiguration;
import com.temple.service.cdi.TempleBean;
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
@Named("languageBean")
@SessionScoped
@TempleBean
public class TempleLanguageBean extends AbstractCDIBean implements LanguageBean, Serializable {

	private static final long serialVersionUID = 1L;

	private LocaleBundle bundle;

	private Language currentLanguage;

	TempleLanguageBean() {
		super();
	}

	@Inject
	TempleLanguageBean(@ApplicationBean CDIConfiguration conf) {
		this();
		this.currentLanguage = conf.getDefaultLanguage();
		this.bundle = conf.getLocaleBundle();
	}

	@Override
	public Language getCurrentLanguage() {
		return this.currentLanguage;
	}

	@Override
	public void setCurrentLanguage(Language language) {
		this.currentLanguage = language;
		// FIXME shouldn't be here !
		FacesContext.getCurrentInstance().getViewRoot().setLocale(language.getLocale());
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
	public String getString(String key, Object... parameters) {
		return this.getString(new DefaultLocaleViewable(key, parameters, this.bundle));
	}

	@Override
	public String getDetailedString(LocaleViewable lv) {
		return LocaleStringViewHelper.createLocaleString(lv, this.currentLanguage, true);
	}

	@Override
	public String getDetailedString(String key, Object... parameters) {
		return this.getDetailedString(new DefaultLocaleViewable(key, parameters, this.bundle));
	}
}
