package com.temple.web.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.temple.Module;
import com.temple.util.AbstractLogger;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class AbstractFilter extends AbstractLogger implements Filter {

	/**
	 * Constructor.
	 */
	protected AbstractFilter() {
		super(Module.WEB);
	}

	@Override
	public final void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (this.doFilter((HttpServletRequest) request, (HttpServletResponse) response)) {
			chain.doFilter(request, response);
		}
	}

	/**
	 * TODOC
	 * 
	 * @param request
	 * @param response
	 * @return <code>true</code> if chain.doFilter should be called after this method resolved, <code>false</code>
	 *         otherwise
	 * @throws IOException
	 * @throws ServletException
	 */
	protected abstract boolean doFilter(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.info("Initializing " + this.getClass().getSimpleName());
	}

	@Override
	public void destroy() {
		this.info("Destroying " + this.getClass().getSimpleName());
	}
}
