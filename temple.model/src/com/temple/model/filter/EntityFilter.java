package com.temple.model.filter;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.temple.model.TempleEntity;

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
	TypedQuery<E> createTypedQuery(EntityManager em);
}
