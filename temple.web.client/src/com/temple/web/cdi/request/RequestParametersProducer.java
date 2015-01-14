package com.temple.web.cdi.request;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.temple.cdi.ApplicationBean;
import com.temple.cdi.CDISessionParameter;
import com.temple.web.cdi.AbstractTempleWebBean;
import com.temple.web.cdi.WebConfiguration;
import com.temple.web.cdi.WebRequestParameter;
import com.temple.web.cdi.WebRequestParameter.Type;

/**
 * Producer for every request related data.
 *
 * @author Florent Pallaver
 * @version 1.0
 */
@ApplicationScoped
public class RequestParametersProducer extends AbstractTempleWebBean {

	private final int pageParameterIndex;

	private final String homePage;

	private final List<String> commonPages;

	private final List<String> sessionPages;

	@Inject
	RequestParametersProducer(@ApplicationBean WebConfiguration config) {
		super();
		this.pageParameterIndex = config.getPageParameterIndex();
		this.homePage = config.getHomePage();
		this.commonPages = config.getCommonPages();
		this.sessionPages = config.getSessionPages();
	}

	// TOTHINK it seems that data are retrieved before the filter applies
	// InjectionPoint requires @Dependent
	// To not use @Dependent implies that the returned value is proxyable !!!!
	@Produces
	@Dependent
	@WebRequestParameter(type = Type.STRING)
	String getStringRequestParameter(InjectionPoint ip, @RequestParameters List<String> parameters) {
		final WebRequestParameter annotation = ip.getAnnotated().getAnnotation(WebRequestParameter.class);
		final String key = annotation.key().trim();
		final int i = annotation.index();
		final String p = key != null && key.length() > 0 ? FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key)
				: parameters.size() > i ? parameters.get(i) : null;
		return p;
	}

	@Produces
	@Dependent
	@WebRequestParameter(type = Type.INTEGER)
	Integer getIntegerRequestParameter(InjectionPoint ip, @RequestParameters List<String> parameters) {
		final String p = this.getStringRequestParameter(ip, parameters);
		Integer i = null;
		if (!(p == null || p.isEmpty())) {
			try {
				i = Integer.valueOf(p);
			} catch (final NumberFormatException e) {
				this.info(p + " is not an integer - " + e.getMessage());
			}
		}
		return i;
	}

	@Produces
	@Dependent
	@WebRequestParameter(type = Type.BOOLEAN)
	Boolean getBooleanRequestParameter(InjectionPoint ip, @RequestParameters List<String> parameters) {
		return Boolean.valueOf(this.getStringRequestParameter(ip, parameters) != null);
	}

	@Produces
	@Dependent
	@WebRequestParameter(type = Type.PAGE)
	String getCurrentPage(@RequestParameters List<String> parameters, @CDISessionParameter(CDISessionParameter.Type.SIGNED_IN) boolean signedIn) {
		final String page;
		if (parameters.isEmpty()) {
			page = this.homePage;
		} else {
			final String p = parameters.get(this.pageParameterIndex);
			page = signedIn && this.sessionPages.contains(p) || this.commonPages.contains(p) ? p : this.homePage;
		}
		return page;
	}
}
