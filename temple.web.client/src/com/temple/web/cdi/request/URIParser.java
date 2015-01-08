package com.temple.web.cdi.request;

import java.util.List;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
// TODO rename
public interface URIParser {

	/**
	 * TODOC
	 *
	 * @return
	 */
	String getApplicationURI();

	/**
	 * TODOC
	 *
	 * @return
	 */
	List<String> getURIParts();
}
