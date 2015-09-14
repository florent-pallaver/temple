package com.temple.web.cdi;

import java.io.File;
import java.util.List;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public interface WebConfiguration {

	/**
	 * TODOC
	 * 
	 * @return
	 */
	int getPageParameterIndex();

	/**
	 * TODOC
	 * 
	 * @return
	 */
	String getHomePage();

	/**
	 * TODOC
	 * 
	 * @return
	 */
	List<String> getCommonPages();

	/**
	 * TODOC
	 * 
	 * @return
	 */
	List<String> getSessionPages();

	/**
	 * TODOC
	 * Starts with / and doesn't end with /
	 * 
	 * @return
	 */
	String getStaticResourcePathPrefix();
	
	/**
	 * TODOC
	 * @return 
	 */
	File getTempFolder() ;
}
