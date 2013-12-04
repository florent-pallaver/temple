package com.temple.ejb.model;

import com.temple.model.TempleEntity;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public interface PartialResults<E extends TempleEntity> extends Results<E> {

	/**
	 * TODOC
	 * 
	 * @return
	 */
	int getTotalCount();
}
