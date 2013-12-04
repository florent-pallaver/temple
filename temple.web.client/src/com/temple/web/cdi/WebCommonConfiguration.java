package com.temple.web.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

import com.temple.web.cdi.WebApplicationParameter.Type;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
@ApplicationScoped
public class WebCommonConfiguration {

	@Produces
	@Dependent
	@WebApplicationParameter(Type.CONTEXT_PATH)
	private String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();

	WebCommonConfiguration() {}
}
