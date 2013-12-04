package com.temple.web.cdi.request;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Strategy to parse an URL.
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public interface RequestParser {

	/**
	 * TODOC
	 * Don't start with /
	 * 
	 * @param request
	 * @return
	 */
	String getApplicationURI(HttpServletRequest request);

	/**
	 * TODOC
	 * 
	 * @param request
	 * @return
	 */
	List<String> parse(HttpServletRequest request);
}
