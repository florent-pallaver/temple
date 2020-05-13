package com.temple.web.jsf;

import java.util.List;

import javax.enterprise.inject.Instance;
import javax.faces.application.ViewHandler;
import javax.faces.application.ViewHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.temple.web.cdi.WebApplicationParameter;
import com.temple.web.cdi.WebApplicationParameter.Type;
import com.temple.web.cdi.request.RequestParameters;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
// FIXME to be added in the faces-config.xml
public class TempleViewHandler extends ViewHandlerWrapper {

	private final ViewHandler parent;

	@Inject
	@RequestParameters
	private Instance<List<String>> requestParameters;

	@Inject
	@WebApplicationParameter(Type.CONTEXT_PATH)
	private String contextPath;

	public TempleViewHandler(ViewHandler parent) {
		super();
		this.parent = parent;
	}

	@Override
	public ViewHandler getWrapped() {
		return this.parent;
	}

	@Override
	public String getActionURL(FacesContext context, String viewId) {
		// Naturally, the view handler sees the real URL.
		final StringBuilder sb = new StringBuilder(this.contextPath).append('/');
		for (final String p : this.requestParameters.get()) {
			sb.append(p).append('/');
		}
		return sb.toString();
	}
}
