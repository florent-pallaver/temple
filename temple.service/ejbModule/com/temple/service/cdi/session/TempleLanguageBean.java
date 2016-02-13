package com.temple.service.cdi.session;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.temple.service.cdi.ApplicationBean;
import com.temple.service.cdi.CDIConfiguration;
import com.temple.service.cdi.TempleObject;
import com.temple.util.human.Language;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 * @deprecated use an {@link ApplicationBean} instead
 */
@Named("languageBean")
@SessionScoped
@TempleObject
@Deprecated
public class TempleLanguageBean extends AbstractLanguageBean {

	private static final long serialVersionUID = 1L;

	@Inject
	TempleLanguageBean(@ApplicationBean CDIConfiguration conf) {
		super(conf.getDefaultLanguage(), conf.getLocaleBundle());
	}

	@Override
	public void setCurrentLanguage(Language language) {
		super.setCurrentLanguage(language);
		// FIXME shouldn't be here !
		FacesContext.getCurrentInstance().getViewRoot().setLocale(language.getLocale());
	}

}
