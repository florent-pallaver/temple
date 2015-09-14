package com.temple.model.filter;

import com.temple.model.TempleEntity;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 * @param <E>
 */
public interface EntityFilter<E extends TempleEntity> extends Serializable {

	/**
	 * TODOC
	 * 
	 * @param em
	 * @return
	 */
	TypedQuery<? extends E> createTypedQuery(EntityManager em);
}
