package com.temple.service;



/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
@FunctionalInterface
public interface ServiceCaller {

	/**
	 * TODOC
	 * 
	 * @throws ServiceException
	 */
	void call() throws ServiceException;
}