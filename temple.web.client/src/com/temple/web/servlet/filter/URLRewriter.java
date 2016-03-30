package com.temple.web.servlet.filter;

import com.temple.service.cdi.ApplicationBean;
import com.temple.service.cdi.TempleObject;
import com.temple.web.cdi.WebConfiguration;
import com.temple.web.cdi.request.URIParser;
import java.io.IOException;
import javax.faces.application.ResourceHandler;
import javax.inject.Inject;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
// TODOC the fact that it is linked to Faces Servlet !
@WebFilter(urlPatterns = "/*", servletNames = "Faces Servlet", asyncSupported = true)
public class URLRewriter extends AbstractFilter {

	private static final String JAVAX_FACES_RESOURCE = ResourceHandler.RESOURCE_IDENTIFIER.substring(1);

	@Inject
	@ApplicationBean
	private WebConfiguration config;

	@Inject
	@TempleObject
	private URIParser parser;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		super.init(filterConfig);
	}

	@Override
	protected boolean doFilter(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		final String pi = this.parser.getApplicationURI();
		final boolean b = pi == null || pi.startsWith(URLRewriter.JAVAX_FACES_RESOURCE) || pi.startsWith(this.config.getStaticResourcePathPrefix());
		if (b) {
			if (this.isDebugLoggable()) {
				this.debug("letting through " + pi);
			}
		} else {
			if (this.isDebugLoggable()) {
				this.debug("Dispatching " + pi);
			}
			// FIXME index.jsf to parameter !
//			request.getRequestDispatcher("/test.jsf").forward(request, response);
			request.getRequestDispatcher("/index.jsf").forward(request, response);
		}
		return b;
	}

	/**
	 * @return the {@link WebConfiguration}
	 */
	protected final WebConfiguration getConfig() {
		return this.config;
	}
}
