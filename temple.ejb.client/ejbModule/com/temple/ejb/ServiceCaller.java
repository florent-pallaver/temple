package com.temple.ejb;


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