package com.temple.model.filter;

import java.io.Serializable;

import com.temple.model.TempleEntity;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public interface FilterOrder<E extends TempleEntity> extends Serializable {

	/**
	 * TODOC
	 * 
	 * @return
	 */
	OrderCriteria<E> getCriteria();
}
