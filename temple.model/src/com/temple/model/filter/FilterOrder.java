package com.temple.model.filter;

import com.temple.model.TempleEntity;
import java.io.Serializable;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 * @param <E> the class to which this {@link FilterOrder} may apply to
 */
public interface FilterOrder<E extends TempleEntity> extends Serializable {

	/**
	 * @return <code>true</code> if this is an ascending order, <code>false</code> if it is descending.
	 */
	boolean isAsc() ;
	
	/**
	 * @return the {@OrderCriteria} 
	 */
	OrderCriteria<E> getCriteria();
	
	/**
	 * Tells if an entity class is compatible with this FilterOrder.
	 * @param c an entity class
	 * @return <code>true</code> if this FilterOrder can apply to the given entity class, <code>false</code> otherwise.
	 */
	boolean accepts(Class<? extends TempleEntity> c) ;
	
}
