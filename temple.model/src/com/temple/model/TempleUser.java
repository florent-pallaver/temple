package com.temple.model;


/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public interface TempleUser extends TempleEntity {

	@Override
	Integer getId();

	/**
	 * TODOC
	 * 
	 * @return
	 */
	String getLogin();
}
