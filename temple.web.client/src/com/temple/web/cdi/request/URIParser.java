package com.temple.web.cdi.request;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import com.temple.Module;
import com.temple.bean.AbstractTempleBean;
import com.temple.cdi.TempleBean;
import com.temple.web.cdi.WebApplicationParameter;
import com.temple.web.cdi.WebApplicationParameter.Type;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
// FORMAT = /page/[objectId/]
@ApplicationScoped
@TempleBean
public class URIParser extends AbstractTempleBean implements RequestParser {

	private static final String pathSeparator = "/";

	private final int offset;

	@Inject
	URIParser(@WebApplicationParameter(Type.CONTEXT_PATH) String contextPath) {
		super(Module.WEB);
		this.offset = contextPath.length() + 1;
	}

	@Override
	public String getApplicationURI(HttpServletRequest request) {
		final String uri = request.getRequestURI();
		return uri.substring(this.offset);
	}

	@Produces
	@Dependent
	@RequestParameters
	@Override
	public List<String> parse(HttpServletRequest request) {
		final List<String> parameters;
		final String path = this.getApplicationURI(request);
		if (path.length() > 0) {
			final String[] params = path.split(URIParser.pathSeparator);
			// we ignore the requested page on the page
			final int paramCount = params.length - (path.endsWith(URIParser.pathSeparator) ? 0 : 1);
			parameters = new ArrayList<>(paramCount);
			for (int i = 0; i < paramCount; i++) {
				parameters.add(params[i]);
			}
		} else {
			parameters = Collections.emptyList();
		}
		return parameters;
	}
}
