package com.temple.model.filter;

import java.io.Serializable;

import com.temple.model.TempleEntity;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 * @param <E> the class to which this {@link FilterOrder} may apply to
 */
public interface FilterOrder<E extends TempleEntity> extends Serializable {

	/**
	 * TODOC
	 *
	 * @return
	 */
	OrderCriteria<E> getCriteria();
}
