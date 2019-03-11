package com.temple.util;

/**
 * TODOC
 * 
 * @author crezik
 * @version 1.0
 */
public interface Observable {

	/**
	 * TODOC
	 * 
	 * @param observer - TODOC
	 */
	void addObserver(Observer observer);

	/**
	 * TODOC
	 * 
	 * @param observer - TODOC
	 */
	void removeObserver(Observer observer);

	/**
	 * TODOC
	 */
	void notifyObservers();

}
