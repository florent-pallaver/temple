package com.temple.model.filter;

import com.temple.model.TempleEntity;
import java.io.Serializable;

import javax.persistence.metamodel.SingularAttribute;

/**
 * TODOC
 *
 * @param <E> the class to which this {@link FilterOrder} may apply to
 */
public interface FilterOrder<E extends TempleEntity> extends Serializable {

	boolean isAsc() ;
	
	SingularAttribute<? super E, ? extends Comparable<?>> getField();
	
	/**
	 * Tells if an entity class is compatible with this FilterOrder.
	 * @param c an entity class
	 * @return <code>true</code> if this FilterOrder can apply to the given entity class, <code>false</code> otherwise.
	 */
	boolean accepts(Class<? extends TempleEntity> c) ;
	
}
