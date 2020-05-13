package com.temple.web.cdi.request;

import com.temple.service.cdi.TempleObject;
import com.temple.web.cdi.WebApplicationParameter;
import com.temple.web.cdi.WebApplicationParameter.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
// FORMAT = /page/[objectId/]
@RequestScoped
@TempleObject
public class TempleURIParser extends AbstractRequestBean implements URIParser {

	private static final long serialVersionUID = 1L;

	private static final String pathSeparator = "/";

	private final int offset;

	private final String uri;

	@Produces
	@Dependent
	@RequestParameters
	private final List<String> parsedParameters;

	@Inject
	TempleURIParser(@WebApplicationParameter(Type.CONTEXT_PATH) String contextPath, HttpServletRequest request) {
		super();
		this.offset = contextPath.length() + 1;
		this.uri = request.getRequestURI().substring(this.offset);
		this.parsedParameters = this.parse(request);
	}

	@Override
	public String getApplicationURI() {
		return this.uri;
	}

	@Override
	public List<String> getURIParts() {
		return this.parsedParameters;
	}

	private List<String> parse(HttpServletRequest request) {
		final List<String> parameters;
		if (this.uri.length() > 0) {
			final String[] params = this.uri.split(TempleURIParser.pathSeparator);
			// we ignore the requested page on the page
			final int paramCount = params.length - (this.uri.endsWith(TempleURIParser.pathSeparator) ? 0 : 1);
			parameters = new ArrayList<>(paramCount);
			for (int i = 0; i < paramCount; i++) {
				parameters.add(params[i]);
			}
		} else {
			parameters = Collections.emptyList();
		}
		if (this.isDebugLoggable()) {
			this.debug(parameters);
		}
		return parameters;
	}
}
